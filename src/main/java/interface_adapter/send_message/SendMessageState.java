package main.java.interface_adapter.send_message;

import main.java.entity.ChatChannel;

public class SendMessageState {
    //TODO
    private String message = "";
    private String messageError = null;
    private String user_id = "";
    private String user_id_error = null;
    private ChatChannel channel = null;
    private String channelError = null;
    private boolean messageSentSuccessful = false;

    private String errorMessage = null;

    public SendMessageState(SendMessageState copy) {
        this.messageSentSuccessful = copy.messageSentSuccessful;
        this.errorMessage = copy.errorMessage;
    }

    public SendMessageState() {
    }

    public String getMessage(){
        return message;
    }

    public void setMessgae(String messgae){
        this.message = messgae;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id){
        this.user_id = user_id;
    }

    public ChatChannel getChannel(){
        return this.channel;
    }

    public void setChannel(ChatChannel channel){
        this.channel = channel;
    }


    public boolean isMessageSentSuccessful() {
        return messageSentSuccessful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setMessageSentSuccessful(boolean messageSentSuccessful) {
        this.messageSentSuccessful = messageSentSuccessful;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return "SendMessageState{" +
                "messageSentSuccessful=" + messageSentSuccessful +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
