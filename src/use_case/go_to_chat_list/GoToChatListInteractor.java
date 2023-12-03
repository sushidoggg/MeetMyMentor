package use_case.go_to_chat_list;

import entity.ChatChannel;
import entity.User;

import java.util.ArrayList;

public class GoToChatListInteractor implements GoToChatListInputBoundary {
    final GoToChatListDataAccessInterface goToChatPageDataAccessObject;
    final GoToChatListOutputBoundary goToChatListPresenter;
    public GoToChatListInteractor(GoToChatListDataAccessInterface goToChatPageDataAccessObject, GoToChatListOutputBoundary goToChatListPresenter) {
        this.goToChatPageDataAccessObject = goToChatPageDataAccessObject;
        this.goToChatListPresenter = goToChatListPresenter;
    }
    @Override
    public void execute() {
        try {
            ArrayList<ChatChannel> chatChannels = goToChatPageDataAccessObject.getAllChatChannels();
            GoToChatListOutputData goToChatListOutputData = new GoToChatListOutputData(chatChannels, goToChatPageDataAccessObject.getCurrentUser());
            goToChatListPresenter.prepareSuccessView(goToChatListOutputData);
            System.out.println("I am at step 3");
        } catch (RuntimeException e) {
            goToChatListPresenter.prepareFailView(e.toString());
            System.out.println("I am at step 4");
        }
    }

}
