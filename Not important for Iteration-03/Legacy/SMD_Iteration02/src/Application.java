//TODO: Current user is set to manager by default for testing, set it back to a blank user when Login classes reassign it
//TODO: Implement login constructors/getters/etc. (Should just have to uncomment them when done)
//TODO: Make sure to have main go to login menu when implemented, not store management
import java.sql.*;
/**
 * Application
 *
 * Handles try-catching of SQLite DB connection as well as instantiates
 *  most presentation/controller classes.
 *
 * Contains main class
 * */
public class Application {
    //Singleton Pattern
    private static Application instance;
    public static Application getInstance(){ if(instance==null){instance=new Application();} return instance; }

    //Main Components of Application
    private Connection connection;
    public Connection getConnection() { return connection; }

    private SQLDataAdapter sqlDataAdapter;
    public SQLDataAdapter getSqlDataAdapter() { return sqlDataAdapter; }

    private static Employee currentUser;
    public static Employee getCurrentUser() { return currentUser; }
    public static void setCurrentUser(Employee newUser) { currentUser = newUser; }

    //Presentable classes (Views) and their getters. Also creates setters if applicable.
    private ProductScreenView productScreenView = new ProductScreenView();
        public ProductScreenView getProductScreenView() { return productScreenView; }
    private CheckoutScreenView checkoutScreenView = new CheckoutScreenView();
        public CheckoutScreenView getCheckoutScreenView() { return checkoutScreenView; }
    private LoginScreenView loginScreenView = new LoginScreenView();
        public LoginScreenView getLoginScreenView() { return loginScreenView; }
    private UsersScreenView usersScreenView = new UsersScreenView();
        public UsersScreenView getUsersScreenView() { return usersScreenView; }
    private StoreManagementSystemView storeManagementSystemView = new StoreManagementSystemView();
        public StoreManagementSystemView getStoreManagementSystemView() { return storeManagementSystemView; }
        public void setStoreManagementSystemView(StoreManagementSystemView newView) {storeManagementSystemView = newView; }
    private ProfileScreenView profileScreenView;
        public ProfileScreenView getProfileScreenView() { return profileScreenView; }
        public void setProfileScreenView(ProfileScreenView newView) {profileScreenView = newView; }
    private InventoryScreenView inventoryScreenView = new InventoryScreenView();
        public InventoryScreenView getInventoryScreenView() { return inventoryScreenView; }
    private BusinessReportScreenView businessReportScreenView = new BusinessReportScreenView(sqlDataAdapter);
        public BusinessReportScreenView getBusinessReportScreenView() { return businessReportScreenView; }
    private ViewInventoryScreenView viewInventoryScreenView;
        public ViewInventoryScreenView getViewInventoryScreenView() { return viewInventoryScreenView; }
        public void setViewInventoryScreenView(ViewInventoryScreenView newView) { viewInventoryScreenView = newView; }

    //Controller classes and their getters. Also gets setters if applicable.
    private ProductScreenController productScreenController;
        public ProductScreenController getProductScreenController() { return productScreenController; }
    private CheckoutScreenController checkoutScreenController;
        public CheckoutScreenController getCheckoutScreenController() { return checkoutScreenController; }
    private LoginScreenController loginScreenController;
        public LoginScreenController getLoginController() { return loginScreenController; }
    private UsersScreenController usersScreenController;
        public UsersScreenController getUsersScreenController() { return usersScreenController; }
    private StoreManagementSystemController storeManagementSystemController;
        public StoreManagementSystemController getStoreManagementSystemController() { return storeManagementSystemController; }
        public void setStoreManagementSystemController(StoreManagementSystemController controllerIn) { storeManagementSystemController = controllerIn; }
    private ProfileScreenController profileScreenController;
        public ProfileScreenController getProfileScreenController() { return profileScreenController; }
        public void setProfileScreenController(ProfileScreenController controllerIn) { profileScreenController = controllerIn; }
    private InventoryScreenController inventoryScreenController;
        public InventoryScreenController getInventoryScreenController() { return inventoryScreenController; }
    private BusinessReportScreenController businessReportScreenController;
        public BusinessReportScreenController getBusinessReportScreenController() { return businessReportScreenController; }
    private ViewInventoryScreenController viewInventoryScreenController;
        public ViewInventoryScreenController getViewInventoryScreenController() { return viewInventoryScreenController; }
        public void setViewInventoryScreenController(ViewInventoryScreenController newController) { viewInventoryScreenController = newController; }

    //Constructor
    private Application() {
        //  Creates SQLite DB connection
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:store.db");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: SQLite is not installed");
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("ERROR: SQLite database is not ready");
            System.exit(2);
        }

        //Creates an SQL Data Adapter which is connected to the SQLite DB connection
        sqlDataAdapter = new SQLDataAdapter(connection);

        //Creating View classes
        storeManagementSystemView = new StoreManagementSystemView();
        productScreenView = new ProductScreenView();
        checkoutScreenView = new CheckoutScreenView();
        inventoryScreenView = new InventoryScreenView();
        loginScreenView = new LoginScreenView();
        profileScreenView = new ProfileScreenView(sqlDataAdapter);
        businessReportScreenView = new BusinessReportScreenView(sqlDataAdapter);
        usersScreenView = new UsersScreenView();
        viewInventoryScreenView = new ViewInventoryScreenView();

        //Creating Controller classes
        storeManagementSystemController = new StoreManagementSystemController(storeManagementSystemView, sqlDataAdapter);
        usersScreenController = new UsersScreenController(usersScreenView, sqlDataAdapter);
        productScreenController = new ProductScreenController(productScreenView, sqlDataAdapter);
        checkoutScreenController = new CheckoutScreenController(checkoutScreenView, sqlDataAdapter);
        inventoryScreenController = new InventoryScreenController(inventoryScreenView, sqlDataAdapter);
        loginScreenController = new LoginScreenController(loginScreenView, sqlDataAdapter);
        profileScreenController = new ProfileScreenController(profileScreenView, sqlDataAdapter);
        businessReportScreenController = new BusinessReportScreenController(businessReportScreenView, sqlDataAdapter);
        viewInventoryScreenController = new ViewInventoryScreenController(viewInventoryScreenView, sqlDataAdapter);

    }

    public static void main(String[] args) {
        //Application.getInstance().getLoginScreenView().setVisible(true);
        //Application.getInstance().getStoreManagementSystemView().setVisible(true);
        Application.getInstance().getLoginScreenView().setVisible(true);
    }
}
