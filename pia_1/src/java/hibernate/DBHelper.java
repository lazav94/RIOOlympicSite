package hibernate;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DBHelper implements Serializable {

    public User getUserByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        User user = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE username = '" + username + "'");
            List<User> users = query.list();
            if (!users.isEmpty()) {
                user = users.get(0);
            }
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return user;
    }
    
    public List<User> getAllDelegate(){
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<User> users = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE type=" + 1);
            users = query.list();

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return users;
    }

    public List<User> getAllUserByNationId(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<User> users = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE id_nation=" + id + " AND type=" + 2);
            users = query.list();

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return users;
    }

    public User getUserByIdNation(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        User user = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE id_nation=" + id);
            List<User> users = query.list();
            if (!users.isEmpty()) {
                user = users.get(0);
            }
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return user;
    }

    public Nation getNationByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        Nation nation = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Nation WHERE name = '" + name + "'");
            List<Nation> nations = query.list();
            if (!nations.isEmpty()) {
                nation = nations.get(0);
            }
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return nation;
    }

    public Discipline getAllDisciplineByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        Discipline discipline = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Discipline WHERE name = '" + name + "'");
            List<Discipline> disciplins = query.list();
            if (!disciplins.isEmpty()) {
                discipline = disciplins.get(0);
            }
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return discipline;
    }

    public void updateUser(User updatedUser) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.update(updatedUser);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            session.close();
        }

    }

    public void updateSportsman(Sportsman s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.update(s);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            session.close();
        }

    }

    public void updateTeam(Team t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.update(t);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            session.close();
        }

    }

    public void addNewUser(User newUser) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(newUser);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void addSportsmanDiscipline(Sportsman s, Discipline d) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;

        try {
            tr = session.beginTransaction();

            //session.save(s);
            s.getDisciplins().add(d);
            session.update(s);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

    }

    public List<UserRequest> getAllUserRequests() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<UserRequest> userRequests = null;

        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM UserRequest");
            userRequests = query.list();

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return userRequests;
    }

    public void addNewUserRequest(UserRequest u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(u);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void deleteUser(User u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.delete(u);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void deleteUserRequest(UserRequest u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.delete(u);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    public List<Nation> getAllNation() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Nation> nations = null;

        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Nation");
            nations = query.list();

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return nations;
    }

    public List<Sport> getAllSports() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Sport> sports = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Sport");
            sports = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return sports;
    }

    public List<Sportsman> getAllSportsman() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Sportsman> sportsman = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Sportsman");
            sportsman = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return sportsman;
    }

    public List<Sportsman> getAllSportsmanByNationAndSportIDAndSex(Integer id, Integer id_sport, String sex) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Sportsman> sportsman = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Sportsman Where id_nation=" + id + " and id_sport=" + id_sport + " and sex='" + sex + "'");
            sportsman = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return sportsman;
    }

    public List<Sportsman> getAllSportsmanByNationAndSportID(Integer id, Integer id_sport) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Sportsman> sportsman = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Sportsman Where id_nation=" + id + " and id_sport=" + id_sport);
            sportsman = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return sportsman;
    }

    public List<Sportsman> getAllSportsmanByNationId(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Sportsman> sportsman = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Sportsman Where id_nation=" + id);
            sportsman = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return sportsman;
    }

    public void addNewSportman(Sportsman newSportman) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(newSportman);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    public Sport getSportByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        Sport sport = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Sport WHERE name = '" + name + "'");
            List<Sport> sports = query.list();
            if (!sports.isEmpty()) {
                sport = sports.get(0);
            }
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return sport;
    }

    public Sportsman getSportsManBySportmanName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        Sportsman sportsman = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Sportsman WHERE name = '" + name + "'");
            List<Sportsman> sportsmen = query.list();
            if (!sportsmen.isEmpty()) {
                sportsman = sportsmen.get(0);
            }
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return sportsman;
    }

    public Discipline getDisciplineByName(String discipline) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        Discipline d = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Discipline WHERE name = '" + discipline + "'");
            List<Discipline> disciplins = query.list();
            if (!disciplins.isEmpty()) {
                d = disciplins.get(0);
            }
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return d;
    }

    public List<Discipline> getAllDisciplineBySportId(Integer sportID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Discipline> disciplins = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Discipline Where id_sport='" + sportID + "'");
            disciplins = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return disciplins;
    }

    public List<Discipline> getAllDiscipline() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Discipline> disciplins = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Discipline");
            disciplins = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return disciplins;
    }

    public Integer getSportIdBySportName(String sport) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        Integer sportID = 0;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Sport WHERE name = '" + sport + "'");
            List<Sport> sports = query.list();
            if (!sports.isEmpty()) {
                sportID = sports.get(0).id;
            }
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }

        return sportID;
    }

    public void addNewSport(Sport newSport) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(newSport);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void addNewDiscipline(Discipline newDiscipline) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(newDiscipline);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    public List<Record> getAllRecords() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Record> records = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Record");
            records = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return records;
    }

    public List<Team> getAllTeams() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Team> teams = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Team");
            teams = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return teams;
    }

    public void addTeam(Team newTeam) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(newTeam);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    public List<Team> getAllTeamsBySport(Integer idu, Integer id, String sex, boolean a) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Team> teams = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Team Where id_sport=" + id + " and id_user=" + idu + " and sex='" + sex + "' and applied=" + a);
            teams = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return teams;
    }

    public List<Team> getAllTeamsByUser(Integer idu, boolean a) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Team> teams = null;
        try {
            tr = session.beginTransaction();

            Query query = session.createQuery("FROM Team Where id_user=" + idu + " and applied=" + a);
            teams = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return teams;
    }

    public List<Team> getAllTeamsByDiscipline(Integer idu, Integer id, String sex, boolean a) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Team> teams = null;
        try {
            tr = session.beginTransaction();

            Query query = session.createQuery("FROM Team Where id_discipline=" + id + " and id_user=" + idu + " and sex='" + sex + "' and applied=" + a);
            teams = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return teams;
    }

    public List<Team> getAllTeamsBySportAndDiscipline(Integer idu, Integer ids, Integer idd, String sex, boolean a) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Team> teams = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Team Where id_sport=" + ids + " and id_discipline=" + idd + " and id_user=" + idu + " and sex='" + sex + "' and applied=" + a);
            teams = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return teams;
    }

    public List<Team> getAllTeamRequest() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Team> teams = null;
        try {
            tr = session.beginTransaction();

            Query query = session.createQuery("FROM Team Where applied=1 and id_tournament IS NULL");
            teams = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return teams;
    }

    public void addNewTournament(Tournament newTournament) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(newTournament);

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    public List<Tournament> getAllTournaments() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Tournament> tournaments = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Tournament");
            tournaments = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return tournaments;
    }

    public Tournament getTournamentsBySportIdAndSex(Integer ids, String sex) {
          Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        Tournament tournament = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Tournament where id_sport=" + ids + " and sex='" + sex + "'");
            List<Tournament> tournaments = query.list();
            if(tournaments.size() > 0)
                tournament = tournaments.get(0);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return tournament;
    }
    
     public Tournament getTournamentsId(Integer id) {
          Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        Tournament tournament = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Tournament where id=" + id);
            List<Tournament> tournaments = query.list();
            if(tournaments.size() > 0)
                tournament = tournaments.get(0);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return tournament;
    }

    public Tournament getTournamentsBySportIdAndDisciplineIdAndSex(Integer ids, Integer idd, String sex) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        Tournament tournament = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Tournament where id_sport=" + ids + " and id_discipline=" + idd + " and sex='" + sex + "'");
            List<Tournament> tournaments = query.list();
            if(tournaments.size() > 0)
                tournament = tournaments.get(0);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return tournament;
    }

    public void updateTournament(Tournament updatedTournament) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.update(updatedTournament);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            session.close();
        }

    }
    
    
    public List<Tournament> getAllValidTournaments() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Tournament> tournaments = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Tournament where valid=" + 1);
            tournaments = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return tournaments;
    }
    
    
     public List<Tournament> getAllDelegateTournaments(Integer idd) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        List<Tournament> tournaments = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("FROM Tournament where valid=" + 1 + " and id_delegate="+ idd );
            tournaments = query.list();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return tournaments;
    }
    
    public void addNewMatch(Matchh newMatch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(newMatch);

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }
      public void updateMatch(Matchh updatedMatch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.update(updatedMatch);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            session.close();
        }

    }
      public void addNewMatch8(Matchh8 newMatch8) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(newMatch8);

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }
      
       public void updateMatch8(Matchh8 updatedMatch8) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.update(updatedMatch8);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            session.close();
        }

    }
    
    
    

}
