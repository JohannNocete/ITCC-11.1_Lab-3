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
        
        System.out.println("=== Service Management ===");
        System.out.println("1. Add Service");
        System.out.println("2. View Services");
        System.out.println("3. Update Service");
        System.out.println("4. Delete Service");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                addService();
                break;
            case 2:
                viewService();
                break;
            case 3:
                updateService();
                break;
            case 4:
                deleteService();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addService() {
        System.out.print("Enter service name: ");
        String name = scanner.nextLine();
        String query = "INSERT INTO services (name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
            System.out.println("Service added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewService() {
        String query = "SELECT * FROM services";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateService() {
        System.out.print("Enter service ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new service name: ");
        String newName = scanner.nextLine();
        String query = "UPDATE services SET name=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Service updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteService() {
        System.out.print("Enter service ID to delete: ");
        int id = scanner.nextInt();
        String query = "DELETE FROM services WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Service deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void manageInvoices() {
        System.out.println("=== Invoice Management ===");
        System.out.println("1. Add Invoice");
        System.out.println("2. View Invoices");
        System.out.println("3. Add Service to Invoice");
        System.out.println("4. View Client Invoices");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                addInvoice();
                break;
            case 2:
                viewInvoices();
                break;
            case 3:
                addServiceToInvoice();
                break;
            case 4:
                viewClientInvoices();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private static void addInvoice() {
        System.out.print("Enter client ID: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter invoice name: ");
        String invoiceName = scanner.nextLine();
        String query = "INSERT INTO invoices (client_id, invoice_name, date_created) VALUES (?, ?, NOW())";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clientId);
            stmt.setString(2, invoiceName);
            stmt.executeUpdate();
            System.out.println("Invoice added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void viewInvoices() {
        String query = "SELECT * FROM invoices";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("invoice_name") + " (Client ID: " + rs.getInt("client_id") + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void addServiceToInvoice() {
        System.out.print("Enter invoice ID: ");
        int invoiceId = scanner.nextInt();
        System.out.print("Enter service ID: ");
        int serviceId = scanner.nextInt();
        System.out.print("Enter hours for the service: ");
        int hours = scanner.nextInt();
        String query = "INSERT INTO invoice_services (invoice_id, service_id, hours) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, invoiceId);
            stmt.setInt(2, serviceId);
            stmt.setInt(3, hours); 
            stmt.executeUpdate();
            System.out.println("Service added to invoice successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void viewClientInvoices() {
        System.out.print("Enter client ID: ");
        int clientId = scanner.nextInt();
        String query = "SELECT * FROM invoices WHERE client_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("invoice_name") + " (Created on: " + rs.getTimestamp("date_created") + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
