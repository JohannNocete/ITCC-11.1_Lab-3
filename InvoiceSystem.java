import java.sql.*;
import java.util.Scanner;

public class InvoiceSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/va_invoice_db";
    private static final String USER = "root";
    private static final String PASSWORD = "xxxx";
    private static Connection conn;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database.");
            runMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void runMenu() {
        while (true) {
            System.out.println("=== Virtual Assistant Invoice System ===");
            System.out.println("1. Client Management");
            System.out.println("2. Service Management");
            System.out.println("3. Invoice Management");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageClients();
                    break;
                case 2:
                    manageServices();
                    break;
                case 3:
                    manageInvoices();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void manageClients() {
        System.out.println("=== Client Management ===");
        System.out.println("1. Add Client");
        System.out.println("2. View Clients");
        System.out.println("3. Update Client");
        System.out.println("4. Delete Client");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                addClient();
                break;
            case 2:
                viewClients();
                break;
            case 3:
                updateClient();
                break;
            case 4:
                deleteClient();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addClient() {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        String query = "INSERT INTO clients (name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
            System.out.println("Client added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewClients() {
        String query = "SELECT * FROM clients";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateClient() {
        System.out.print("Enter client ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new client name: ");
        String newName = scanner.nextLine();
        String query = "UPDATE clients SET name=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Client updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteClient() {
        System.out.print("Enter client ID to delete: ");
        int id = scanner.nextInt();
        String query = "DELETE FROM clients WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Client deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void manageServices() {
    
    }

    private static void manageInvoices() {

    }
}
