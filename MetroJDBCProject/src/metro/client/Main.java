//package metro.client;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Scanner;
//
//import metro.entity.*;
//import metro.exceptions.*;
//import metro.persistence.*;
//
//public class Main {
//
//	public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        CardDao cardDao = new CardDaoImpl();
//        StationDao stationDao = new StationDaoImpl();
//        SwipeRecordsDao swipeDao = new SwipeRecordsDaoImpl();
//
//        while (true) {
//            System.out.println("\n===== Metro System Menu =====");
//            System.out.println("1. Create Card");
//            System.out.println("2. View Card Balance");
//            System.out.println("3. Add Station");
//            System.out.println("4. View All Stations");
//            System.out.println("5. Swipe In");
//            System.out.println("6. Swipe Out");
//            System.out.println("7. View Swipe Records");
//            System.out.println("0. Exit");
//            System.out.print("Enter choice: ");
//
//            int choice = sc.nextInt();
//            sc.nextLine(); // consume newline
//
//            try {
//                switch (choice) {
//                    case 1:
//                        System.out.print("Enter user name: ");
//                        String name = sc.nextLine();
//                        System.out.print("Enter initial balance: ");
//                        double balance = sc.nextDouble();
//                        Card newCard = cardDao.createCard(name, balance);
//                        System.out.println("Card created successfully: " + newCard);
//                        break;
//
//                    case 2:
//                        System.out.print("Enter card number: ");
//                        int cardNo = sc.nextInt();
//                        Card card = cardDao.getCardById(cardNo);
//                        System.out.println("Card Details: " + card);
//                        break;
//
//                    case 3:
//                        System.out.print("Enter station name: ");
//                        String stationName = sc.nextLine();
//                        stationDao.addStation(new Station(0, stationName));
//                        System.out.println(stationName + "Station added successfully.");
//                        break;
//
//                    case 4:
//                        List<Station> stations = stationDao.getAllStations();
//                        System.out.println("Stations:");
//                        for (Station s : stations) {
//                            System.out.println(s);
//                        }
//                        break;
//
//                    case 5:
//                        System.out.print("Enter card number: ");
//                        int swipeInCard = sc.nextInt();
//                        System.out.print("Enter station id: ");
//                        int swipeInStation = sc.nextInt();
////                        swipeDao.addSwipeRecord(new SwipeRecord(0, swipeInCard, swipeInStation, "IN"));
//                        System.out.println("Swipe In recorded.");
//                        break;
//
//                    case 6:
//                        System.out.print("Enter card number: ");
//                        int swipeOutCard = sc.nextInt();
//                        System.out.print("Enter station id: ");
//                        int swipeOutStation = sc.nextInt();
////                        swipeDao.addSwipeRecord(new SwipeRecord(0, swipeOutCard, swipeOutStation, "OUT"));
//                        System.out.println("Swipe Out recorded.");
//                        break;
//
//                    case 7:
//                        System.out.print("Enter card number: ");
//                        int recCard = sc.nextInt();
//                        List<SwipeRecord> records = swipeDao.getRecordsByCard(recCard);
//                        System.out.println("Swipe Records:");
//                        for (SwipeRecord r : records) {
//                            System.out.println(r);
//                        }
//                        break;
//
//                    case 0:
//                        System.out.println("Exiting Metro System...");
//                        sc.close();
//                        return;
//
//                    default:
//                        System.out.println("Invalid choice! Try again.");
//                }
//            } catch (CardNotFoundException | StationNotFoundException e) {
//                System.out.println("Error: " + e.getMessage());
//            } catch (DatabaseConnectionException e) {
//                System.out.println("Database error: " + e.getMessage());
//            } catch (InvalidAmountException e) {
//                System.out.println("Invalid amount: " + e.getMessage());
//            } catch (SQLException e) {
//                System.out.println("SQL error: " + e.getMessage());
//            } catch (Exception e) {
//                System.out.println("Unexpected error: " + e.getMessage());
//            }
//        }
//    }
//
//}
