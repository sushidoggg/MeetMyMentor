package main.java.app;

import main.java.data_access.GoToChatListDataAccessObject;
import main.java.data_access.RefreshChatPageDataAccessObject;
import main.java.data_access.SendMessageDataAccessObject;
import main.java.entity.ChatChannel;
import main.java.entity.User;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.go_to_chat.GoToChannelState;
import main.java.interface_adapter.go_to_chat.GoToChannelViewModel;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListState;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageState;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageViewModel;
import main.java.interface_adapter.send_message.SendMessageState;
import main.java.interface_adapter.send_message.SendMessageViewModel;

import main.java.view.ChannelView.ChannelView;


import main.java.view.ChatListView.ChatListView;
import main.java.view.FrameModel;
import main.java.view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainForAlysa {
    public static void main(String[] args) {

//         FrameModel application = new FrameModel("Send Message example");
//        application.setVisible(true);

        JFrame application = new FrameModel("Tutoring APP");

        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);  // 所有view加入这个views
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);


        /*
         * 这里手动initialize一个currentuser和chatchannel, state
         * */
        User testUser1 = new User("test1", "test1", "test1");
        User testUser2 = new User("test1", "test1", "test1");

        Map<String, User> testMap = new HashMap<>();
        testMap.put("test1", testUser1);
        testMap.put("test2", testUser2);
        LocalDateTime currentDateTime = LocalDateTime.now();


        ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
        ArrayList<ChatChannel> channels = new ArrayList<>();
        channels.add(channel);

        SendMessageState testState = new SendMessageState(testUser1, channel);
        RefreshChatPageState refreshTestState = new RefreshChatPageState(testUser1, channel);
        GoToChatListState goToChatListState = new GoToChatListState(testUser1, channels);
        GoToChannelState goToChannelState = new GoToChannelState(testUser1, channel);
//        SendMessageState testState = new SendMessageState();
        /*
         * 这里结束手动initialize一个currentuser和chatchannel
         * */

        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel(testUser1, channel, testState);
        SendMessageDataAccessObject sendMessageDataAccessObject;

        RefreshChatPageViewModel refreshChatPageViewModel = new RefreshChatPageViewModel(testUser1, channel, refreshTestState);
        RefreshChatPageDataAccessObject refreshChatPageDataAccessObject;

        GoToChatListViewModel goToChatListViewModel = new GoToChatListViewModel();
        goToChatListViewModel.setState(goToChatListState);
        GoToChatListDataAccessObject goToChatListDataAccessObject;

        GoToChannelViewModel goToChannelViewModel = new GoToChannelViewModel();
        goToChannelViewModel.setState(goToChannelState);



        GoToPersonalProfileViewModel goToPersonalProfileViewModel = new GoToPersonalProfileViewModel();

        try {
            sendMessageDataAccessObject = new SendMessageDataAccessObject(
                    "https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
                    "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5"

            );
            refreshChatPageDataAccessObject = new RefreshChatPageDataAccessObject();
            goToChatListDataAccessObject = new GoToChatListDataAccessObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChannelView channelView = ChannelUseCasesFactory.create(viewManagerModel, sendMessageViewModel, sendMessageDataAccessObject, refreshChatPageViewModel, refreshChatPageDataAccessObject, goToChatListViewModel, goToChatListDataAccessObject);
        views.add(channelView, channelView.viewName);

        ChatListView chatListView = ChatListUsesCaseFactory.create(viewManagerModel, goToChatListViewModel, goToChatListDataAccessObject, goToPersonalProfileViewModel, goToChannelViewModel);
        views.add(chatListView, chatListView.viewName);

        viewManagerModel.setActiveView(channelView.viewName);
        viewManagerModel.firePropertyChanged();

//        application.pack();
        application.setVisible(true);

    }
}
