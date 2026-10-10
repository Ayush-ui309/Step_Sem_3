package main.java.oop_design_and_systems.assigment_problems;

import java.util.*;

public class CodeSprintJudgingDesk {

    static class Student {
        private String name;

        Student(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static class Team {
        private String name;
        private List<Student> members = new ArrayList<>();

        Team(String name) {
            this.name = name;
        }

        boolean addMember(Student student) {
            if (members.size() >= 4) {
                System.out.println("Team cannot have more than 4 members.");
                return false;
            }

            members.add(student);
            return true;
        }

        String getName() {
            return name;
        }

        int getMemberCount() {
            return members.size();
        }
    }

    interface ScoringStrategy {
        double calculateScore(double idea, double execution, double presentation);
    }

    static class InnovationScoring implements ScoringStrategy {
        public double calculateScore(double idea, double execution, double presentation) {
            return idea * 0.50 + execution * 0.30 + presentation * 0.20;
        }
    }

    static class OpenScoring implements ScoringStrategy {
        public double calculateScore(double idea, double execution, double presentation) {
            return (idea + execution + presentation) / 3.0;
        }
    }

    static class Track {
        private String name;
        private ScoringStrategy scoringStrategy;

        Track(String name, ScoringStrategy scoringStrategy) {
            this.name = name;
            this.scoringStrategy = scoringStrategy;
        }

        double calculateScore(double idea, double execution, double presentation) {
            return scoringStrategy.calculateScore(idea, execution, presentation);
        }

        String getName() {
            return name;
        }
    }

    static class Project {
        private String title;
        private Team team;
        private Track track;
        private double score;
        private boolean published;

        Project(String title, Team team, Track track) {
            this.title = title;
            this.team = team;
            this.track = track;
        }

        void evaluate(double idea, double execution, double presentation) {
            if (published) {
                System.out.println("Published scores cannot be changed.");
                return;
            }

            if (idea < 0 || idea > 100 || execution < 0 || execution > 100
                    || presentation < 0 || presentation > 100) {
                System.out.println("Scores must be between 0 and 100.");
                return;
            }

            score = track.calculateScore(idea, execution, presentation);
            System.out.printf("Score for %s: %.2f%n", title, score);
        }

        void publishScore() {
            published = true;
            System.out.printf("Published score for %s: %.2f%n", title, score);
        }

        String getTitle() {
            return title;
        }

        Team getTeam() {
            return team;
        }

        Track getTrack() {
            return track;
        }

        double getScore() {
            return score;
        }

        boolean isPublished() {
            return published;
        }
    }

    static class Hackathon {
        private String name;
        private List<Team> teams = new ArrayList<>();
        private List<Project> projects = new ArrayList<>();
        private Map<Student, Team> studentTeams = new HashMap<>();

        Hackathon(String name) {
            this.name = name;
        }

        boolean registerTeam(Team team) {
            if (team.getMemberCount() < 2 || team.getMemberCount() > 4) {
                System.out.println("A team must have 2 to 4 members.");
                return false;
            }

            for (Student student : getMembers(team)) {
                if (studentTeams.containsKey(student)) {
                    System.out.println(student.getName() + " is already registered in a team.");
                    return false;
                }
            }

            teams.add(team);

            for (Student student : getMembers(team)) {
                studentTeams.put(student, team);
            }

            System.out.println("Team " + team.getName() + " registered successfully.");
            return true;
        }

        private List<Student> getMembers(Team team) {
            return team.members;
        }

        boolean submitProject(Project project) {
            if (!teams.contains(project.getTeam())) {
                System.out.println("Team is not registered.");
                return false;
            }

            if (projects.contains(project)) {
                System.out.println("Project already submitted.");
                return false;
            }

            for (Project existing : projects) {
                if (existing.getTeam() == project.getTeam()) {
                    System.out.println("Each team can submit only one project.");
                    return false;
                }
            }

            projects.add(project);
            System.out.println("Project " + project.getTitle() + " submitted to " + name + ".");
            return true;
        }

        void displayResults() {
            System.out.println("\nHackathon Results");

            for (Project project : projects) {
                if (project.isPublished()) {
                    System.out.printf("%s | Team: %s | Track: %s | Score: %.2f%n",
                            project.getTitle(),
                            project.getTeam().getName(),
                            project.getTrack().getName(),
                            project.getScore());
                }
            }
        }
    }

    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon("Code Sprint");

        Student student1 = new Student("Arun");
        Student student2 = new Student("Priya");
        Student student3 = new Student("Rahul");

        Team team1 = new Team("Team Alpha");
        team1.addMember(student1);
        team1.addMember(student2);

        Team team2 = new Team("Team Beta");
        team2.addMember(student3);
        team2.addMember(new Student("Meena"));

        Track innovation = new Track("Innovation", new InnovationScoring());
        Track open = new Track("Open", new OpenScoring());

        hackathon.registerTeam(team1);
        hackathon.registerTeam(team2);

        Project project1 = new Project("AI Assistant", team1, innovation);
        Project project2 = new Project("Smart Website", team2, open);

        hackathon.submitProject(project1);
        hackathon.submitProject(project2);

        project1.evaluate(90, 80, 85);
        project2.evaluate(75, 85, 80);

        project1.publishScore();
        project2.publishScore();

        project1.evaluate(100, 100, 100);

        hackathon.displayResults();
    }
}