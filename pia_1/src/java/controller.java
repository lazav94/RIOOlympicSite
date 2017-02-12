
import hibernate.DBHelper;
import hibernate.Discipline;
import hibernate.Matchh;
import hibernate.Matchh8;
import hibernate.Nation;
import hibernate.Record;
import hibernate.Sport;
import hibernate.Sportsman;
import hibernate.Team;
import hibernate.Tournament;
import hibernate.User;
import java.io.Serializable;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean
public class controller implements Serializable {

    //DATABASE HELPER
    public static DBHelper dbHelper;

    //MESSAGES
    public static String errorMsg;
    public static String infoMsg;

    static {
        dbHelper = new DBHelper();
        errorMsg = "";
        infoMsg = "";

    }

    //NON REGISTRED
    private List<Nation> nations;
    private String selectedNation;
    private List<Sport> sports;
    private String selectedSport;
    private List<Discipline> disciplins;
    private String selectedDiscipline;
    private String newType;
    private String sex;

    // *** filter
    private List<Sportsman> filtredSportsman;
    private Boolean medal;

    // + ORGANIZER
    // *** add sport
    private String newSport;
    // *** add disciplin
    private String newDiscipline;
    private String selectedSportDiscipline;
    private Integer min;
    private Integer max;
    // *** add tournament
    private Date beginDate;
    private Date endDate;
    private String location;
    private List<Record> records;

    private List<Sportsman> sportsmen;
    private List<Tournament> tournaments;

    //LEADER
    private String newSportsman;
    private String selectedSportsman;
    private Sportsman selSportsman;

    private List<Sportsman> leaderSportsmen;
    private List<Team> teams;

    private List<Team> teamsTournament;

    public static List<Team> teamRequest;

    private List<Sportsman> teamTournamentSportsman;
    private List<Sportsman> breadChrumbsSportsman;

    public static List<Sportsman> nationSpormsman;
    private List<Discipline> disciplineFilter;

    public Discipline selDis;
    private Integer selectedDisciplineId;

    public static List<Team> selectedTeamTournament;

    public static Map<String, Integer> appliedToSport = new HashMap<String, Integer>();
    public Map<String, Integer> appliedSpormanNation;

    private List<User> allDelegate;
    private List<User> avaiableDelegate;

    private String selectedDelegat;

    private List<Matchh> tournamentMatches;
    private List<Matchh8> tournamentMatches8;
    private Integer selectedTournament;
    private Tournament selTournament;

    private Date matchDate;
    private String matchLocation;

    public controller() {
        initZERO();
        refresh();

    }

    private void initZERO() {

        errorMsg = "";
        infoMsg = "";
        selectedNation = "";
        selectedSport = "";
        selectedDiscipline = "";
        newType = "";
        sex = "";
        newSport = "";
        newDiscipline = "";
        selectedSportDiscipline = "";
        min = 0;
        max = 0;
        beginDate = new Date();
        endDate = new Date();

        location = "";
        newSportsman = "";
        selectedSportsman = "";
        selSportsman = null;
        selTournament = null;

    }

    private void refresh() {

        allDelegate = dbHelper.getAllDelegate();
        getAllAvaiableDelegate();

        nations = dbHelper.getAllNation();
        sports = dbHelper.getAllSports();
        sportsmen = dbHelper.getAllSportsman();
        disciplins = dbHelper.getAllDiscipline();

        records = dbHelper.getAllRecords();

        tournaments = dbHelper.getAllTournaments();
        teams = dbHelper.getAllTeams();

        filtredSportsman = sportsmen;

        teamTournamentSportsman = sportsmen;
        breadChrumbsSportsman = sportsmen;

        disciplineFilter = disciplins;

        appliedSpormanNation = new HashMap<>();
        for (Nation n : nations) {
            appliedSpormanNation.put(n.getName(), dbHelper.getAllSportsmanByNationId(n.getId()).size());
        }

    }

    public void addTournament() {
        //PROVERA
        //ako je basket waterpolo ili odbojka 12 igraca mora da ima (ni vise ni manje)

        //ako su individualni sportovi onda moze da ima koliko god > 0
        //tenis i stoni tenis 2^n gde je n > 2
        //dohvati delegata
        User delegat = dbHelper.getUserByUsername(selectedDelegat);

        Sport s = dbHelper.getSportByName(selectedSport);
        Discipline d = null;
        if (s.getDisciplins().size() > 0) {
            d = dbHelper.getDisciplineByName(selectedDiscipline);
        }

        //dohvati turir (mora da postoji)
        Tournament tournament;
        if (d == null) {
            tournament = dbHelper.getTournamentsBySportIdAndSex(s.getId(), sex);
        } else {
            tournament = dbHelper.getTournamentsBySportIdAndDisciplineIdAndSex(s.getId(), d.getId(), sex);
        }

        if (tournament.getSport().getId() == 1 || tournament.getSport().getId() == 2 || tournament.getSport().getId() == 3) {
            if (tournament.getTeams().size() != 4 && tournament.getTeams().size() != 8 && tournament.getTeams().size() != 10 && tournament.getTeams().size() != 12) {
                errorMsg = "You dont have 4 8 10 12 team at this tournament";
                saveMessage();
                return;
            }
        } else if (tournament.getSport().getId() == 6 && (tournament.getDiscipline().getId() == 9 || tournament.getDiscipline().getId() == 10)) {
            if (!powerOfTwo(tournament.getTeams().size())) {
                errorMsg = "You must have power of 2 teams";
                saveMessage();
                return;
            }
        } else if (tournament.getSport().getId() == 8 && (tournament.getDiscipline().getId() == 11 || tournament.getDiscipline().getId() == 12)) {
            if (!powerOfTwo(tournament.getTeams().size())) {
                errorMsg = "You must have power of 2 teams";
                saveMessage();
                return;
            }
        }

        //dodeli mu selektovanog delegata - on ima sve ostalo
        tournament.setDelegat(delegat);

        delegat.getTournaments().add(tournament);
        dbHelper.updateUser(delegat);

        if (delegat.getTournaments().size() == 3) {
            avaiableDelegate.remove(delegat);
        }

        //stavi i neku validaciju!
        tournament.setValid(true);
        dbHelper.updateTournament(tournament);

        infoMsg = "You add new tournament with delegate";
        saveMessage();

    }

    public Tournament newTournament(Sport s, Discipline d) {

        Tournament t = new Tournament(null, s, d, sex, beginDate, endDate, location, false);
        dbHelper.addNewTournament(t);
        tournaments = dbHelper.getAllTournaments();
        infoMsg = "You add new tournament ";
        saveMessage();
        return t;
    }

    public void acceptTeam(Team t) {
        Tournament tournament;

        if (t.getDiscipline() == null) {
            tournament = dbHelper.getTournamentsBySportIdAndSex(t.getSport().getId(), t.getSex());
        } else {
            tournament = dbHelper.getTournamentsBySportIdAndDisciplineIdAndSex(t.getSport().getId(), t.getDiscipline().getId(), t.getSex());
        }

        if (tournament == null) {
            tournament = newTournament(t.getSport(), t.getDiscipline());
        } else if (tournament.getSport().getId() == 1 || tournament.getSport().getId() == 2 || tournament.getSport().getId() == 3) {

        }

        t.setApplied(true);
        t.setTournament(tournament);
        teamsTournament.remove(t);
        dbHelper.updateTeam(t);

        tournament.getTeams().add(t);
        dbHelper.updateTournament(tournament);

        infoMsg = "You approve team";
        saveMessage();

    }

    public void rejectTeam(Team t) {
        t.setApplied(false);
        dbHelper.updateTeam(t);
        teamsTournament.remove(t);
        infoMsg = "You reject team";
        saveMessage();
    }

    public void getMatches() {

        selTournament = dbHelper.getTournamentsId(selectedTournament);
        if (selTournament == null) {
            return;
        }
        boolean haveDisciplins = !selTournament.getSport().getDisciplins().isEmpty();
        if (!haveDisciplins) {
            //Basket, Waterpolo, Walleyball
            tournamentMatches = new ArrayList<>(selTournament.getMatches());
            tournamentMatches8 = null;
        } else if (selTournament.getDiscipline().getMin() == 1) {
            // Individual sports
            tournamentMatches8 = new ArrayList<>(selTournament.getMatches8());

            tournamentMatches8 = selTournament.getMatches8();

            tournamentMatches = null;
        } else {
            // Tenis , Table tennis double!
            tournamentMatches = new ArrayList<>(selTournament.getMatches());
            tournamentMatches8 = null;
        }
    }

    public void editMatches(Matchh match) {
        if (matchDate == null) {
            return;
        }
        int kolo = match.getKolo();

        for (Matchh m : tournamentMatches) {
            if (kolo != 1) {
                if (m.getKolo() < kolo) {
                    boolean needTime = m.getTime() == null;
                    boolean needLocation = m.getLocation().isEmpty();
                    boolean ret = false;
                    if (needTime) {
                        errorMsg = "You need to enter date for previous kolo = " + m.getKolo();
                        saveMessage();
                        ret = true;
                    }
                    if (needLocation) {
                        errorMsg = "You need to enter location for previous kolo = " + m.getKolo();
                        saveMessage();
                        ret = true;
                    }
                    if (ret) {
                        return;
                    }
                }
            }
            if (!Objects.equals(m.getId(), match.getId()) && m.getTime() != null) {

                if (matchDate.compareTo(m.getTime()) == 0 && matchLocation.equals(m.getLocation())) {
                    errorMsg = "You canot have match on this location at this time";
                    saveMessage();
                    return;
                }
            }
        }

        boolean needTime = matchDate == null;
        boolean needLocation = matchLocation.isEmpty();
        boolean ret = false;
        if (needTime) {
            errorMsg = "You need to enter date";
            saveMessage();
            ret = true;
        }
        if (needLocation) {
            errorMsg = "You need to enter location";
            saveMessage();
            ret = true;
        }
        if (ret) {
            return;
        }

        match.setTime(matchDate);
        match.setLocation(matchLocation);

        dbHelper.updateMatch(match);
        infoMsg = "You update date for match id = " + match.getId();

        saveMessage();

        matchDate = null;
        matchLocation = "";

    }

    public void editMatches8(Matchh8 match8) {

    }

    public void cancelMatches() {
        infoMsg = "You cancel editing";
        saveMessage();
    }

    public void randomAlgorithm(Tournament tournament) {
        List<Team> allTeams = tournament.getTeams();

        Collections.shuffle(allTeams); //, new Random(System.nanoTime())
        if (allTeams.isEmpty()) {
            errorMsg = "radnom algo error";
            saveMessage();
            return;
        }

        boolean haveDisciplins = !tournament.getSport().getDisciplins().isEmpty();
        if (!haveDisciplins) {
            List<Team> group1 = new ArrayList<>();
            List<Team> group2 = new ArrayList<>();

            if (allTeams.size() == 4 || allTeams.size() == 8) {
                for (int i = 0; i < allTeams.size(); i++) {
                    group1.add(allTeams.get(i));

                }
                matchTeams(group1, tournament, 1, 0);
                matchTeams(group1, tournament, 0, 0);
            } else if (allTeams.size() == 10 || allTeams.size() == 12) {
                for (int i = 0; i < allTeams.size() / 2; i++) {

                    group1.add(allTeams.get(i));
                    group2.add(allTeams.get(allTeams.size() / 2 + i));

                }
                matchTeams(group1, tournament, 1, 0);
                matchTeams(group2, tournament, 2, 0);
                matchTeams(group1, tournament, 0, 0);
            }

        } else if (tournament.getDiscipline().getMin() == 1 && tournament.getSport().getId() != 6 && tournament.getSport().getId() != 8) {
            // Individual sports
            if (tournament.getSport().getId() == 5) {
                matchTeams(allTeams, tournament, 0, 1);
            } else {
                List<Team> group = new ArrayList<>();
                int q = 1;
                for (int i = 0; i < allTeams.size(); i++) {
                    group.add(allTeams.get(i));

                    if (i != 0 && i % 8 == 0) {
                        group = new ArrayList<>();
                        matchTeams(group, tournament, q, 1);
                        q++;
                    }
                    if (i % 8 != 0 && i == allTeams.size() - 1) {
                        matchTeams(group, tournament, q, 1);
                    }
                }
            }
        } else {
            matchTeams(allTeams, tournament, 0, 2);
        }

    }

    public void matchTeams(List<Team> teams, Tournament tournament, Integer group, int type) {
        switch (type) {
            case 0:
                if (group != 0) {
                    if (teams.size() == 6 || teams.size() == 5) {
                        List<Team> inv = new ArrayList<>(teams);

                        for (int j = 0; j < teams.size() - 1; j++) {
                            Collections.rotate(inv, 5);

                            int kolo;
                            if (j % 2 == 1) {
                                kolo = 2;
                            } else {
                                kolo = 1;
                            }

                            for (int i = 0; i < teams.size() - 1 - j; i++) {
                                if (!Objects.equals(teams.get(j).getId(), inv.get(i).getId())) {
                                    Matchh m = new Matchh(null, teams.get(j), inv.get(i), null, "", kolo++, teams.get(i).getTournament(), null, "", group);
                                    dbHelper.addNewMatch(m);
                                    tournament.getMatches().add(m);
                                    dbHelper.updateTournament(tournament);
                                }
                            }
                        }
                        /*  for (int i = 0; i < teams.size() - 1; i++) {

                            for (int j = i + 1, kolo = 1 + (i == 1 ? 0 : 1); j < teams.size(); j++) {
                                Matchh m = new Matchh(null, teams.get(i), teams.get(j), null, "", kolo++, teams.get(i).getTournament(), null, "", group);
                                    dbHelper.addNewMatch(m);

                                    tournament.getMatches().add(m);
                                    dbHelper.updateTournament(tournament);
                            }
                        }*/
                    } else if (teams.size() == 4 || teams.size() == 8) {
                        for (int i = 0; i < teams.size() - 1; i = i + 2) {
                            Matchh m1 = new Matchh(null, teams.get(i), teams.get(i + 1), null, "", 1, teams.get(i).getTournament(), null, "", 0);
                            dbHelper.addNewMatch(m1);

                            tournament.getMatches().add(m1);
                            dbHelper.updateTournament(tournament);
                        }
                    }
                } else {
                    int kolo = 6;
                    for (int i = 0; i < 8; i++) {
                        if (i == 4 || i == 6 || i == 7) {
                            kolo++;
                        }
                        Matchh m = new Matchh(null, null, null, null, "", kolo, teams.get(0).getTournament(), null, "", 0);
                        dbHelper.addNewMatch(m);

                        tournament.getMatches().add(m);
                        dbHelper.updateTournament(tournament);

                    }
                }

                break;

            case 1:
                Matchh8 m = null;
                List<Matchh8> l = new ArrayList<>();
                for (int i = 0; i < teams.size(); i++) {
                    if (m == null) {
                        m = new Matchh8(null, null, "", group, teams.get(i).getTournament());
                        dbHelper.addNewMatch8(m);
                    }

                    m.getTeams().add(teams.get(i));
                    dbHelper.updateMatch8(m);

                    teams.get(i).getMatchh8().add(m);
                    dbHelper.updateTeam(teams.get(i));

                    l.add(m);

                }
                tournament.setMatches8(l);
                dbHelper.updateTournament(tournament);
                break;
            case 2:
                for (int i = 0; i < teams.size() - 1; i = i + 2) {
                    Matchh mm = new Matchh(null, teams.get(i), teams.get(i + 1), null, "", 1, teams.get(i).getTournament(), null, "", 0);

                    dbHelper.addNewMatch(mm);
                    tournament.getMatches().add(mm);
                    dbHelper.updateTournament(tournament);

                    if ((i + 1) % 4 == 3) {
                        Matchh mmm = new Matchh(null, null, null, null, "", 2, teams.get(i).getTournament(), null, "", 0);
                        dbHelper.addNewMatch(mmm);
                        tournament.getMatches().add(mmm);
                        dbHelper.updateTournament(tournament);
                    }
                    if ((i + 1) % 8 == 7) {
                        Matchh mmm = new Matchh(null, null, null, null, "", 3, teams.get(i).getTournament(), null, "", 0);
                        dbHelper.addNewMatch(mmm);
                        tournament.getMatches().add(mmm);
                        dbHelper.updateTournament(tournament);
                    }

                    if ((i + 1) % 16 == 15) {
                        Matchh mmm = new Matchh(null, null, null, null, "", 4, teams.get(i).getTournament(), null, "", 0);
                        dbHelper.addNewMatch(mmm);
                        tournament.getMatches().add(mmm);
                        dbHelper.updateTournament(tournament);
                    }
                    if ((i + 1) % 32 == 31) {
                        Matchh mmm = new Matchh(null, null, null, null, "", 3, teams.get(i).getTournament(), null, "", 0);
                        dbHelper.addNewMatch(mmm);
                        tournament.getMatches().add(mmm);
                        dbHelper.updateTournament(tournament);
                    }
                    dbHelper.updateTournament(tournament);
                }
                Matchh mmm = new Matchh(null, null, null, null, "", 3, teams.get(0).getTournament(), null, "", 0);
                dbHelper.addNewMatch(mmm);

                tournament.getMatches().add(mmm);
                dbHelper.updateTournament(tournament);

                break;
            default:
                break;
        }
    }

    public void chooseTournament(Tournament t) {

        teamTournamentSportsman = new ArrayList<>();
        for (Team i : teams) {
            if (Objects.equals(t.getSport().getId(), i.getSport().getId())) {
                if (t.getDiscipline() != null) {
                    if (Objects.equals(t.getDiscipline().getId(), i.getDiscipline().getId())) {
                        teamsTournament.add(i);
                    }
                } else {
                    teamsTournament.add(i);
                }

            }
        }

    }

    public void getAllAvaiableDelegate() {
        avaiableDelegate = new ArrayList<>();
        for (User delegat : allDelegate) {
            if (delegat.getTournaments().size() < 3) {
                this.avaiableDelegate.add(delegat);
            }
        }

    }

    public static int getSportSize(Sport sport) {
        return dbHelper.getAllSportsmanByNationAndSportID(userController.user.getNation().getId(), sport.getId()).size();
    }

    public void selectTeamsTournament() {
        teamsTournament = new ArrayList<>();
        for (Team t : teamRequest) {
            if (selectedDiscipline.equals("")) {
                if (t.getSport().getName().equals(selectedSport) && sex.equals(t.getSex()) && t.getTournament() == null) {

                    teamsTournament.add(t);
                }
            } else if (t.getSport().getName().equals(selectedSport) && t.getDiscipline().getName().equals(selectedDiscipline) && sex.equals(t.getSex())) {
                teamsTournament.add(t);
            }
        }

    }

    public void selectSportTournament() {
        Integer sportId = dbHelper.getSportIdBySportName(selectedSport);
        disciplins = dbHelper.getAllDisciplineBySportId(sportId);
        if (disciplins.isEmpty()) {
            selectedDiscipline = "";
        }
        if (teamRequest != null) {
            selectTeamsTournament();
        }
    }

    public void filterIndex() {
        Discipline d = dbHelper.getAllDisciplineByName(selectedDiscipline);
        Sport s = null;
        if (!("".equals(selectedSport))) {
            s = dbHelper.getSportByName(selectedSport);
        }

        List<Sportsman> temp = new ArrayList<>();

        if (s == null && d == null) {
            return;
        } else if (d == null) {
            return;
        } else {
            for (Sportsman i : sportsmen) {
                for (Discipline di : i.getDisciplins()) {
                    if (s == null) {
                        if (Objects.equals(di.getId(), d.getId())) {
                            temp.add(i);
                        }
                    } else if (Objects.equals(i.getSport().getId(), s.getId()) && Objects.equals(di.getId(), d.getId())) {
                        temp.add(i);
                    }
                }
            }

            List<Sportsman> result = new ArrayList<>();

            if (!temp.isEmpty()) {
                for (Sportsman ss : temp) {
                    if (filtredSportsman.contains(ss)) {
                        result.add(ss);
                    }
                }
                filtredSportsman = result;
            } else {
                filtredSportsman = new ArrayList<>();
                return;
            }

        }
    }

    public boolean filter() {
        Discipline d = dbHelper.getAllDisciplineByName(selectedDiscipline);
        Sport s = dbHelper.getSportByName(selectedSport);

        nationSpormsman = dbHelper.getAllSportsmanByNationId(userController.user.getNation().getId());
        filtredSportsman = new ArrayList<>();

        if (s == null && d == null) {
            filtredSportsman = nationSpormsman;
            disciplineFilter = disciplins;

        } else {
            for (Sportsman i : nationSpormsman) {
                if (d == null || "".equals(selectedDiscipline)) {
                    if (Objects.equals(i.getSport().getId(), s.getId())) {
                        filtredSportsman.add(i);
                        disciplineFilter = new ArrayList(s.getDisciplins());
                    }
                } else {
                    for (Discipline di : i.getDisciplins()) {
                        if (s == null) {
                            if (Objects.equals(di.getId(), d.getId())) {
                                filtredSportsman.add(i);
                            }
                        } else if (Objects.equals(i.getSport().getId(), s.getId()) && Objects.equals(di.getId(), d.getId())) {
                            filtredSportsman.add(i);
                        }

                    }
                }
            }
            return true;
        }

        return false;
    }

    public void controllerBread(int ii) {
        switch (ii) {
            case 0:
                filtredSportsman = nationSpormsman;
                selectedSport = "";
                selectedDiscipline = "";
                disciplineFilter = disciplins;
                break;
            case 1:
                Sport s = dbHelper.getSportByName(selectedSport);
                filtredSportsman = new ArrayList<>();
                for (Sportsman i : nationSpormsman) {
                    if (Objects.equals(i.getSport().getId(), s.getId())) {
                        filtredSportsman.add(i);
                    }
                }

                disciplineFilter = new ArrayList(s.getDisciplins());
                selectedDiscipline = "";
                break;
            case 2:
                Discipline d = dbHelper.getAllDisciplineByName(selectedDiscipline);
                filtredSportsman = new ArrayList<>();
                for (Sportsman i : nationSpormsman) {
                    for (Discipline di : i.getDisciplins()) {
                        if (Objects.equals(di.getId(), d.getId())) {
                            filtredSportsman.add(i);
                        }
                    }
                }
                selectedSport = "";
                break;
        }
    }

    public void makeTeamRequest(Team t) {

        Tournament tournament;
        if (t.getDiscipline() == null) {
            tournament = dbHelper.getTournamentsBySportIdAndSex(t.getSport().getId(), t.getSex());
        } else {
            tournament = dbHelper.getTournamentsBySportIdAndDisciplineIdAndSex(t.getSport().getId(), t.getDiscipline().getId(), t.getSex());
        }

        if (tournament != null) {
            errorMsg = "Tournament already excist, you can't add this team to tournament";
            saveMessage();
            return;
        }

        int teamSize = t.getSportsmen().size();
        int min, max;

        boolean haveDisciplin = !dbHelper.getAllDisciplineBySportId(t.getSport().getId()).isEmpty();

        if (haveDisciplin == false) {
            min = t.getSport().getMin();
            max = t.getSport().getMax();
        } else {
            min = t.getDiscipline().getMin();
            max = t.getDiscipline().getMax();
        }

        if (teamSize < min) {
            errorMsg = "You need more sportsman in this team";
            saveMessage();
            return;
        }
        if (teamSize > max) {
            errorMsg = "You cant add more sportsman in this team";
            saveMessage();
            return;
        }

        t.setApplied(true);
        dbHelper.updateTeam(t);

        userController.user.setTeams(new HashSet<>(dbHelper.getAllTeamsByUser(userController.user.getId(), false)));

    }

    public void addToTeam() {
        Sport s = dbHelper.getSportByName(selectedSport);
        Discipline d = dbHelper.getDisciplineByName(selectedDiscipline);
        Sportsman sm = dbHelper.getSportsManBySportmanName(selectedSportsman);

        boolean haveDisciplin = !dbHelper.getAllDisciplineBySportId(s.getId()).isEmpty();

        boolean status;
        if (haveDisciplin == false && s.getMin() >= 1 && s.getMax() >= 1) {
            status = addSportsmanToTeamNoDiscipline(s, sm, sex);
        } else if (d.getMin() > 1 && d.getMax() > 1) {
            status = addSportsmanToTeamWithDiscipline(s, d, sm, sex);
        } else {
            status = addSporsmanIndividual(s, d, sm, sex);
        }

        if (status == true) {
            infoMsg = "You add: " + sm.getName() + " to Sport: " + s.getName();
            saveMessage();

        }

        teams = dbHelper.getAllTeamsByUser(userController.user.getId(), false);

    }

    private boolean addSporsmanIndividual(Sport s, Discipline d, Sportsman sm, String sex) {
        Team t = getTeamBySportDiscipline(s, d, sex);

        if (t != null && excistSportmanInTeam(sm, t)) {
            errorMsg = "Individual: You allready add this sportsman in this sport and discipline";
            saveMessage();
            return false;
        }

        t = new Team(null, userController.user, s, d, sex, false);
        t.getSportsmen().add(sm);
        dbHelper.addTeam(t);

        sm.getTeams().add(t);
        dbHelper.updateSportsman(sm);

        userController.user.getTeams().add(t);
        dbHelper.updateUser(userController.user);
        return true;
    }

    private boolean addSportsmanToTeamWithDiscipline(Sport s, Discipline d, Sportsman sm, String sex) {
        Team t = getTeamBySportDiscipline(s, d, sex);
        if (t == null) {
            t = new Team(null, userController.user, s, d, sex, false);
            dbHelper.addTeam(t);
        } else {
            if (excistSportmanInTeam(sm, t)) {
                errorMsg = "You allready add this sportsman in this team";
                saveMessage();
                return false;

            }
            if (getTeamSize(t) >= d.getMax()) {
                errorMsg = "This team is full, you can't add more sportsman to this team";
                saveMessage();
                return false;
            }
        }

        t.getSportsmen().add(sm);
        dbHelper.updateTeam(t);

        sm.getTeams().add(t);
        dbHelper.updateSportsman(sm);

        userController.user.getTeams().add(t);
        dbHelper.updateUser(userController.user);
        return true;
    }

    private boolean addSportsmanToTeamNoDiscipline(Sport s, Sportsman sm, String sex) {
        Team t = getTeamBySport(s, sex);
        if (t == null) {
            t = new Team(null, userController.user, s, null, sex, false);
            dbHelper.addTeam(t);
        } else {
            if (excistSportmanInTeam(sm, t)) {
                errorMsg = "You allready add this sportsman in this team";
                saveMessage();
                return false;

            }
            if (getTeamSize(t) >= s.getMax()) {
                errorMsg = "This team is full, you can't add more sportsman to this team";
                saveMessage();
                return false;
            }
        }

        t.getSportsmen().add(sm);
        dbHelper.updateTeam(t);

        sm.getTeams().add(t);
        dbHelper.updateSportsman(sm);

        userController.user.getTeams().add(t);
        dbHelper.updateUser(userController.user);
        return true;
    }

    private int getTeamSize(Team t) {
        return t.getSportsmen().size();
    }

    private boolean excistSportmanInTeam(Sportsman sm, Team t) {
        for (Sportsman i : t.getSportsmen()) {
            if (i.getId() == sm.getId()) {
                return true;
            }
        }
        return false;
    }

    private boolean excistTeamBySport(Sport s, String sex) {
        return dbHelper.getAllTeamsBySport(userController.user.getId(), s.getId(), sex, false) != null;
    }

    private boolean excistTeamBySportDiscipline(Sport s, Discipline d, String sex) {
        return dbHelper.getAllTeamsBySportAndDiscipline(userController.user.getId(), s.getId(), d.getId(), sex, false) != null;
    }

    private Team getTeamBySport(Sport s, String sex) {
        for (Team i : userController.user.getTeams()) {
            if (Objects.equals(i.getSport().getId(), s.getId()) && sex.equals(i.getSex())) {
                return i;
            }
        }
        return null;
    }

    private Team getTeamBySportDiscipline(Sport s, Discipline d, String sex) {
        for (Team i : userController.user.getTeams()) {
            if (Objects.equals(i.getSport().getId(), s.getId()) && sex.equals(i.getSex()) && Objects.equals(i.getDiscipline().getId(), d.getId())) {
                return i;
            }
        }
        return null;

    }

    public void leaderGetSportman() {
        Discipline d = dbHelper.getAllDisciplineByName(selectedDiscipline);
        List<Sportsman> s = new ArrayList<>();
        leaderSportsmen = new ArrayList<>();
        if (d != null) {
            s.addAll(d.getSportsmen());
            for (Sportsman i : s) {
                if (i.getNation().getName().equals(userController.user.getNation().getName()) && sex.equals(i.getSex())) {
                    leaderSportsmen.add(i);
                }
            }

        }
    }

    public void leaderGetSportAndSportsman() {
        Integer sportId = dbHelper.getSportIdBySportName(selectedSport);
        disciplins = dbHelper.getAllDisciplineBySportId(sportId);
        leaderSportsmen = new ArrayList<>();
        if (disciplins.isEmpty()) {
            leaderSportsmen = sportsmen = dbHelper.getAllSportsmanByNationAndSportIDAndSex(userController.user.getNation().getId(), sportId, sex);
        }

    }

    public void selectSportSporsman() {

        Sportsman sm = dbHelper.getSportsManBySportmanName(selectedSportsman);

        disciplins = dbHelper.getAllDisciplineBySportId(sm.getSport().getId());

    }

    public void onClick() {
        sports = dbHelper.getAllSports();
    }

    public void addSport() {
        dbHelper.addNewSport(new Sport(null, newSport, min, max));

        infoMsg = "You add : " + newSport + " to Sport";
        saveMessage();

        sports = dbHelper.getAllSports();

    }

    public void addSportsman() {
        Sport sport = dbHelper.getSportByName(selectedSport);
        Nation nation = userController.user.getNation();
        Sportsman s = dbHelper.getSportsManBySportmanName(newSportsman);
        if (s == null) {
            s = new Sportsman(null, newSportsman, sport, nation, sex, medal);
            dbHelper.addNewSportman(s);
        }
        Discipline d;

        if (!sport.getDisciplins().isEmpty()) {
            d = dbHelper.getDisciplineByName(selectedDiscipline);
            if (d != null) {
                dbHelper.addSportsmanDiscipline(s, d);
                dbHelper.updateSportsman(s);
            }
        }

        infoMsg = "You add : " + newSportsman;
        saveMessage();

        sportsmen = dbHelper.getAllSportsman();

    }

    public void addDiscipline() {
        Sport sport = dbHelper.getSportByName(selectedSportDiscipline);

        boolean typeIT = true;
        if (min == 1 && max == 1) {
            typeIT = false;
        }

        Discipline d = new Discipline(null, sport, newDiscipline, min, max, typeIT);
        dbHelper.addNewDiscipline(d);
        infoMsg = "You add : " + newDiscipline + " to Discipline for Sport: " + selectedSportDiscipline;
        saveMessage();

        disciplins = dbHelper.getAllDiscipline();
    }

    public static void saveMessage() {

        if (!errorMsg.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Message: " + errorMsg));
            errorMsg = "";
        }
        if (!infoMsg.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Message: " + infoMsg));
            infoMsg = "";
        }
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

    private boolean powerOfTwo(int n) {
        if (n % 2 == 1) {
            return false;
        }

        while (n > 1) {
            n /= 2;
            if (n == 2) {
                return true;
            }
            if (n == 3) {
                return false;
            }
        }
        return false;

    }

    public DBHelper getDbHelper() {
        return dbHelper;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getSelectedSportDiscipline() {
        return selectedSportDiscipline;
    }

    public void setSelectedSportDiscipline(String selectedSportDiscipline) {
        this.selectedSportDiscipline = selectedSportDiscipline;
    }

    public String getInfoMsg() {
        return infoMsg;
    }

    public Sportsman getSelSportsman() {
        return selSportsman;
    }

    public void setSelSportsman(Sportsman selSportsman) {
        this.selSportsman = selSportsman;
    }

    public List<Nation> getNations() {
        return nations;
    }

    public void setNations(List<Nation> nations) {
        this.nations = nations;
    }

    public String getSelectedNation() {
        return selectedNation;
    }

    public List<Sportsman> getLeaderSportsmen() {
        return leaderSportsmen;
    }

    public void setLeaderSportsmen(List<Sportsman> leaderSportsmen) {
        this.leaderSportsmen = leaderSportsmen;
    }

    public void setSelectedNation(String selectedNation) {
        this.selectedNation = selectedNation;
    }

    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    public String getSelectedSportsman() {
        return selectedSportsman;
    }

    public void setSelectedSportsman(String selectedSportsman) {
        this.selectedSportsman = selectedSportsman;
    }

    public String getSelectedSport() {
        return selectedSport;
    }

    public void setSelectedSport(String selectedSport) {
        this.selectedSport = selectedSport;
    }

    public String getSelectedDiscipline() {
        return selectedDiscipline;
    }

    public void setSelectedDiscipline(String selectedDiscipline) {
        this.selectedDiscipline = selectedDiscipline;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Discipline> getDisciplins() {
        return disciplins;
    }

    public List<Sportsman> getSportsmen() {
        return sportsmen;
    }

    public void setSportsmen(List<Sportsman> sportsmen) {
        this.sportsmen = sportsmen;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public void setDisciplins(List<Discipline> disciplins) {
        this.disciplins = disciplins;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNewSport() {
        return newSport;
    }

    public void setNewSport(String newSport) {
        this.newSport = newSport;
    }

    public String getNewDiscipline() {
        return newDiscipline;
    }

    public void setNewDiscipline(String newDiscipline) {
        this.newDiscipline = newDiscipline;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public String getNewSportsman() {
        return newSportsman;
    }

    public void setNewSportsman(String newSportsman) {
        this.newSportsman = newSportsman;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public String getNewType() {
        return newType;
    }

    public void setNewType(String newType) {
        this.newType = newType;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Sportsman> getFiltredSportsman() {
        return filtredSportsman;
    }

    public void setFiltredSportsman(List<Sportsman> filtredSportsman) {
        this.filtredSportsman = filtredSportsman;
    }

    public Boolean getMedal() {
        return medal;
    }

    public void setMedal(Boolean medal) {
        this.medal = medal;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Sportsman> getTeamTournamentSportsman() {
        return teamTournamentSportsman;
    }

    public void setTeamTournamentSportsman(List<Sportsman> teamTournamentSportsman) {
        this.teamTournamentSportsman = teamTournamentSportsman;
    }

    public List<Sportsman> getBreadChrumbsSportsman() {
        return breadChrumbsSportsman;
    }

    public void setBreadChrumbsSportsman(List<Sportsman> breadChrumbsSportsman) {
        this.breadChrumbsSportsman = breadChrumbsSportsman;
    }

    public List<Team> getTeamsTournament() {
        return teamsTournament;
    }

    public void setTeamsTournament(List<Team> teamsTournament) {
        this.teamsTournament = teamsTournament;
    }

    public List<Sportsman> getNationSpormsman() {
        return nationSpormsman;
    }

    public void setNationSpormsman(List<Sportsman> nationSpormsman) {
        this.nationSpormsman = nationSpormsman;
    }

    public List<Team> getTeamRequest() {
        return teamRequest;
    }

    public void setTeamRequest(List<Team> teamRequest) {
        this.teamRequest = teamRequest;
    }

    public Discipline getSelDis() {
        return selDis;
    }

    public void setSelDis(Discipline selDis) {
        this.selDis = selDis;
    }

    public List<Discipline> getDisciplineFilter() {
        return disciplineFilter;
    }

    public void setDisciplineFilter(List<Discipline> disciplineFilter) {
        this.disciplineFilter = disciplineFilter;
    }

    public Integer getSelectedDisciplineId() {
        return selectedDisciplineId;
    }

    public void setSelectedDisciplineId(Integer selectedDisciplineId) {
        this.selectedDisciplineId = selectedDisciplineId;
    }

    public Map<String, Integer> getAppliedToSport() {
        return appliedToSport;
    }

    public void setAppliedToSport(Map<String, Integer> appliedToSport) {
        this.appliedToSport = appliedToSport;
    }

    public List<Team> getSelectedTeamTournament() {
        return selectedTeamTournament;
    }

    public void setSelectedTeamTournament(List<Team> selectedTeamTournament) {
        this.selectedTeamTournament = selectedTeamTournament;
    }

    public List<User> getAvaiableDelegate() {
        return avaiableDelegate;
    }

    public void setAvaiableDelegate(List<User> avaiableDelegate) {
        this.avaiableDelegate = avaiableDelegate;
    }

    public List<User> getAllDelegate() {
        return allDelegate;
    }

    public void setAllDelegate(List<User> allDelegate) {
        this.allDelegate = allDelegate;
    }

    public String getSelectedDelegat() {
        return selectedDelegat;
    }

    public void setSelectedDelegat(String selectedDelegat) {
        this.selectedDelegat = selectedDelegat;
    }

    public List<Matchh> getTournamentMatches() {
        return tournamentMatches;
    }

    public void setTournamentMatches(List<Matchh> tournamentMatches) {
        this.tournamentMatches = tournamentMatches;
    }

    public Integer getSelectedTournament() {
        return selectedTournament;
    }

    public void setSelectedTournament(Integer selectedTournament) {
        this.selectedTournament = selectedTournament;
    }

    public Tournament getSelTournament() {
        return selTournament;
    }

    public void setSelTournament(Tournament selTournament) {
        this.selTournament = selTournament;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchLocation() {
        return matchLocation;
    }

    public void setMatchLocation(String matchLocation) {
        this.matchLocation = matchLocation;
    }

    public List<Matchh8> getTournamentMatches8() {
        return tournamentMatches8;
    }

    public void setTournamentMatches8(List<Matchh8> tournamentMatches8) {
        this.tournamentMatches8 = tournamentMatches8;
    }

    public Map<String, Integer> getAppliedSpormanNation() {
        return appliedSpormanNation;
    }

    public void setAppliedSpormanNation(Map<String, Integer> appliedSpormanNation) {
        this.appliedSpormanNation = appliedSpormanNation;
    }

}
