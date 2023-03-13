package com.example.lab4.controller;

import com.example.lab4.domain.*;
import com.example.lab4.repository.UserDbRepo;
import com.example.lab4.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignedInController implements Initializable {
    private User currentUser = new User();
    UserDbRepo userRepo = new UserDbRepo("jdbc:postgresql://localhost:5432/SocialNetwork", "postgres", "alex", new UserValidator());
    UserService service = new UserService(userRepo);
    @FXML
    private Button logoutButton;
    @FXML
    public Button showAllUsersButton;
    @FXML
    public Button showFriendsButton;
    @FXML
    public Button showRequestsButton;
    @FXML
    public Button sendRequestButton;
    @FXML
    public Button acceptRequestButton;
    @FXML
    public Button deleteFriendButton;
    @FXML
    TableView<User> tableAllUsers = new TableView<>();
    @FXML
    TableColumn<User, String> firstNameColumn = new TableColumn<>();
    @FXML
    TableColumn<User, String> lastNameColumn = new TableColumn<>();
    @FXML
    TableView<User> tableFriends = new TableView<>();
    @FXML
    TableColumn<User, String> friendFirstNameColumn = new TableColumn<>();
    @FXML
    TableColumn<User, String> friendLastNameColumn = new TableColumn<>();
    @FXML
    public TableView<RequestViewModel> tableRequests;
    @FXML
    public TableColumn<RequestViewModel, String> requestFirstNameColumn;
    @FXML
    public TableColumn<RequestViewModel, String> requestLastNameColumn;
    @FXML
    public TableColumn<RequestViewModel, Status> requestStatusColumn;
    @FXML
    public TableColumn<RequestViewModel, LocalDateTime> requestDateColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CurrentUser user = CurrentUser.getInstance();
        this.currentUser = this.service.getUser(user.getFirstName(), user.getLastName());

        this.tableAllUsers.setVisible(false);
        this.tableFriends.setVisible(false);
        this.tableRequests.setVisible(false);

        this.sendRequestButton.setDisable(true);
        this.acceptRequestButton.setDisable(true);
        this.deleteFriendButton.setDisable(true);

        this.sendRequestButton.setVisible(false);
        this.acceptRequestButton.setVisible(false);
        this.deleteFriendButton.setVisible(false);

        populateUsers();
        populateFriends();
        populateRequests();
    }

    public void handleLogout() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lab4/hello-view.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) logoutButton.getScene().getWindow();
        window.setScene(new Scene(root,720, 480));
    }

    public void handleShowAllUsers() {
        this.tableAllUsers.setVisible(true);
        this.tableFriends.setVisible(false);
        this.tableRequests.setVisible(false);

        this.sendRequestButton.setDisable(false);
        this.acceptRequestButton.setDisable(true);
        this.deleteFriendButton.setDisable(true);

        this.sendRequestButton.setVisible(true);
        this.acceptRequestButton.setVisible(false);
        this.deleteFriendButton.setVisible(false);
    }

    public void handleShowFriends() {
        this.tableFriends.setVisible(true);
        this.tableAllUsers.setVisible(false);
        this.tableRequests.setVisible(false);

        this.deleteFriendButton.setDisable(false);
        this.sendRequestButton.setDisable(true);
        this.acceptRequestButton.setDisable(true);

        this.deleteFriendButton.setVisible(true);
        this.sendRequestButton.setVisible(false);
        this.acceptRequestButton.setVisible(false);
    }

    public void handleShowRequests() {
        this.tableRequests.setVisible(true);
        this.tableAllUsers.setVisible(false);
        this.tableFriends.setVisible(false);

        this.acceptRequestButton.setDisable(false);
        this.sendRequestButton.setDisable(true);
        this.deleteFriendButton.setDisable(true);

        this.acceptRequestButton.setVisible(true);
        this.sendRequestButton.setVisible(false);
        this.deleteFriendButton.setVisible(false);
    }

    public void handleSendRequest() {
        User friend = tableAllUsers.getSelectionModel().getSelectedItem();
        if (friend != null)
            service.sendFriendRequest(currentUser.getId(), friend.getId());
        updateTables();
    }

    public void handleAcceptRequest() {
        RequestViewModel viewModel = tableRequests.getSelectionModel().getSelectedItem();
        if (viewModel != null && viewModel.getStatus() == Status.PENDING) {
            User friend = new User(viewModel.getFriendFirstName(), viewModel.getFriendLastName());
            friend.setId((long) friend.hashCode());
            service.acceptFriendRequest(currentUser.getId(), friend.getId());
        }
        updateTables();
    }

    public void handleDeleteFriend() {
        User friend = tableFriends.getSelectionModel().getSelectedItem();
        if (friend != null)
            service.deleteFriendship(currentUser.getId(), friend.getId());
        updateTables();
    }

    private void populateUsers() {
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        ObservableList<User> users = FXCollections.observableArrayList();

        for (User u : this.service.getAllUsers()) {
            if (!u.equals(this.currentUser))
                users.add(u);
        }

        tableAllUsers.setItems(users);
    }

    private void populateFriends() {
        this.friendFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.friendLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        ObservableList<User> friends = FXCollections.observableArrayList();

        for (User u : this.service.getUserFriends(currentUser)) {
            friends.add(u);
        }

        tableFriends.setItems(friends);
    }

    private void populateRequests() {
        this.requestFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("friendFirstName"));
        this.requestLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("friendLastName"));
        this.requestStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        this.requestDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        ObservableList<RequestViewModel> viewModels = FXCollections.observableArrayList();

        for (Friendship friendship : this.service.getAllFriendships()) {
            if (Objects.equals(friendship.getLeft(), currentUser.getId())) {
                User friend = this.service.getByID(friendship.getRight());
                String friendFirstName = friend.getFirstName();
                String friendLastName = friend.getLastName();
                Status status = friendship.getStatus();
                LocalDateTime date = friendship.getDate();
                RequestViewModel viewModel = new RequestViewModel(friendFirstName, friendLastName, status, date);
                viewModels.add(viewModel);
            }
        }
        tableRequests.setItems(viewModels);
    }

    public void updateTables(){
        populateUsers();
        populateFriends();
        populateRequests();
    }
}
