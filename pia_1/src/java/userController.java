
import hibernate.Nation;
import hibernate.Sport;
import hibernate.Sportsman;
import hibernate.Team;
import hibernate.User;
import hibernate.UserRequest;
import java.io.Serializable;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@SessionScoped
@ManagedBean
public class userController implements Serializable {

    //CURRENT USER
    public static User user;

    //LOGIN
    private String usernameLogIn;
    private String passwordLogIn;

    //CHANGE PASSWORD
    private String oldPassword;

    //SIGNUP
    private String usernameSignUp;
    private String passwordSignUp;
    private String passwordConfirm;
    private String firstName;
    private String lastName;
    private String email;
    private String nation;
    private Integer type;

    private List<UserRequest> userRequests;

    private int sizeOfNationalLeader;

    private static final String EMAIL_PATTERN
            = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    public userController() {

        refreshString();
        user = null;
        sizeOfNationalLeader = 0;
        userRequests = controller.dbHelper.getAllUserRequests();
    }

    public final void refreshString() {
        usernameLogIn = "";
        passwordLogIn = "";
        oldPassword = "";
        usernameSignUp = "";
        passwordSignUp = "";
        firstName = "";
        nation = "";
        lastName = "";
        email = "";
    }

    public String logIn() {

        user = controller.dbHelper.getUserByUsername(usernameLogIn);
        if (user == null) {
            controller.errorMsg = "Invalid username, please try again";
            saveMessage();
            return null;
        }
        if (!user.getPassword().equals(passwordLogIn)) {
            checkPassword(passwordLogIn);
            controller.errorMsg = "Invalid password,please try again";
            saveMessage();
            return null;
        }
        if (!user.getValid()) {
            controller.errorMsg = "This user is not yet approved by organizator!";
            saveMessage();
            return null;
        }
        switch (user.getType()) {
            case 0:

                refreshString();
                controller.teamRequest = controller.dbHelper.getAllTeamRequest();
                controller.selectedTeamTournament = controller.teamRequest;
               
                userRequests = controller.dbHelper.getAllUserRequests();
                return "organizer?faces-redirected=true";
            case 1:
                refreshString();
                user.setTournaments(new HashSet<>(controller.dbHelper.getAllDelegateTournaments(user.getId())));
                return "delegate?faces-redirected=true";
            case 2:

                Set<Team> teams = new HashSet<>(controller.dbHelper.getAllTeamsByUser(user.getId(), false));
                user.setTeams(teams);
                Set<Sportsman> s = new HashSet<>(controller.dbHelper.getAllSportsmanByNationId(user.getNation().getId()));
                controller.nationSpormsman = new ArrayList<>(s);
                sizeOfNationalLeader = controller.nationSpormsman.size();
                for (Sport sport : controller.dbHelper.getAllSports()) {
                    controller.appliedToSport.put(sport.getName(), controller.getSportSize(sport));
                }
                refreshString();
                return "nacionalLeader?faces-redirected=true";
        }
        return "pnf?faces-redirected=true";
    }

    public String changePassword() {

        user = controller.dbHelper.getUserByUsername(usernameLogIn);
        if (user == null) {
            controller.errorMsg = "Invalid username, please try again";
            saveMessage();
            return null;
        }
        if (checkPassword(oldPassword) == false) {
            return null;
        }
        if (!user.getPassword().equals(oldPassword)) {
            controller.errorMsg = "Invalid old password,please try again";
            saveMessage();
            return null;
        }

        if (checkPassword(passwordLogIn) == false) {
            return null;
        }
        if (oldPassword.equals(passwordLogIn)) {
            controller.errorMsg = "New password can not be same as old password";
            saveMessage();
            return null;
        }
        user.setPassword(passwordLogIn);
        controller.dbHelper.updateUser(user);

        user = null;
        oldPassword = "";
        passwordLogIn = "";

        controller.infoMsg = "User: " + usernameLogIn + " sucessfully change password";
        saveMessage();
        
        return "login?faces-redirected=true";
    }

    public String signUp() {
        if (checkPassword(passwordSignUp) &&  checkPassword(passwordConfirm)){

            if(!passwordConfirm.equals(passwordSignUp)){
                controller.errorMsg = "Passwords not the same";
                saveMessage();
                return null;
            }
            
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {

                Nation n = controller.dbHelper.getNationByName(nation);
                User u = new User(0, usernameSignUp, passwordSignUp, firstName, lastName, n, email, type, false);
                controller.dbHelper.addNewUser(u);
                u = controller.dbHelper.getUserByUsername(usernameSignUp);

                UserRequest ur = new UserRequest(0, u);
                controller.dbHelper.addNewUserRequest(ur);

                userRequests = controller.dbHelper.getAllUserRequests();
                refreshString();
                return "index?faces-redirected=true";

            } else {
                controller.errorMsg = "This is not email address";
                saveMessage();
                return null;
            }
        }

        return null;
    }
    
    public String logout(){
       /* user = null;
        return "index?faces-redirected=true";*/
        
     
        user=null;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        
        
        
        return "index?faces-redirect=true";
    
        
    }

    public void approve(UserRequest ur) {
        User u = ur.getUser();
        FacesContext context = FacesContext.getCurrentInstance();

        List<User> sameNationUsers = controller.dbHelper.getAllUserByNationId(u.getNation().getId());

        if (u.getType() == 2 && sameNationUsers.size() >= 2) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "Allready hava leader for this nation, this user is rejected"));
            controller.dbHelper.deleteUser(ur.getUser());
            controller.dbHelper.deleteUserRequest(ur);
        } else {

            u.setValid(true);
            controller.dbHelper.updateUser(u);
            controller.dbHelper.deleteUserRequest(ur);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "You approved new user"));
        }
        userRequests = controller.dbHelper.getAllUserRequests();
    }

    public void reject(UserRequest ur) {
        controller.dbHelper.deleteUserRequest(ur);
        controller.dbHelper.deleteUser(ur.getUser());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "You rejected user"));
        userRequests = controller.dbHelper.getAllUserRequests();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        userController.user = user;
    }

    public String getUsernameLogIn() {
        return usernameLogIn;
    }

    public void setUsernameLogIn(String usernameLogIn) {
        this.usernameLogIn = usernameLogIn;
    }

    public String getPasswordLogIn() {
        return passwordLogIn;
    }

    public void setPasswordLogIn(String passwordLogIn) {
        this.passwordLogIn = passwordLogIn;
    }

    public String getUsernameSignUp() {
        return usernameSignUp;
    }

    public void setUsernameSignUp(String usernameSignUp) {
        this.usernameSignUp = usernameSignUp;
    }

    public String getPasswordSignUp() {
        return passwordSignUp;
    }

    public void setPasswordSignUp(String passwordSignUp) {
        this.passwordSignUp = passwordSignUp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<UserRequest> getUserRequests() {
        return userRequests;
    }

    public void setUserRequests(List<UserRequest> userRequests) {
        this.userRequests = userRequests;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public int getSizeOfNationalLeader() {
        return sizeOfNationalLeader;
    }

    public void setSizeOfNationalLeader(int sizeOfNationalLeader) {
        this.sizeOfNationalLeader = sizeOfNationalLeader;
    }

    public void saveMessage() {

        if (!controller.errorMsg.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Message: " + controller.errorMsg));
            controller.errorMsg = "";
        }
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    private boolean checkPassword(String password) {
        boolean ret = true;
        FacesContext context = FacesContext.getCurrentInstance();
        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasLowerCase = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");

        if (!hasUpperCase) {
            ret = false;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Password: " + "Must have an uppercase Character"));
        }
        if (!hasLowerCase) {
            ret = false;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Password: " + "Must have an lowercase Character"));
        }
        if (!hasDigit) {
            ret = false;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Password: " + "Must have an digit "));
        }
        if (!(isLowerCase(password.charAt(0)) || isUpperCase(password.charAt(0)))) {
            ret = false;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Password: " + "First character must be letter"));
        }

        int numOfLowerLetter = 0,
                numOfDigit = 0,
                numOfSpecial = 0;
        for (int i = 0; i < password.length(); i++) {
            if (isLowerCase(password.charAt(i))) {
                numOfLowerLetter++;
            } else if (isDigit(password.charAt(i))) {
                numOfDigit++;
            } else if (!Character.isLetter(password.charAt(i)) && !Character.isSpaceChar(password.charAt(i))) {
                numOfSpecial++;
            }
        }

        if (numOfLowerLetter < 3) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Password: " + "Mush have 3 lower case letter"));
            ret = false;
        }

        if (numOfDigit < 2) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Password: " + "Mush have 2 digit"));
            ret = false;
        }
        if (numOfSpecial < 2) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Password: " + "Mush have 2 special character"));
            ret = false;
        }

        for (int i = 0; i < password.length() - 3; i++) {
            char first = password.charAt(i);
            char second = password.charAt(i + 1);
            char third = password.charAt(i + 2);

            if (first == second && second == third) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Password: " + "Can't be 3 same character next to each other"));
                ret = false;
            }
        }

        return ret;
    }

}
