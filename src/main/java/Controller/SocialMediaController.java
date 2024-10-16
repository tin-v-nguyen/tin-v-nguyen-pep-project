package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        accountService = new AccountService();
        messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::patchMessageHandler);

        return app;
    }

    private void patchMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(context.body(), Message.class);
        message.setMessage_id(Integer.valueOf(context.pathParam("message_id")));
        Message newMessage = messageService.patchMessage(message);
        if(newMessage != null) {
            context.status(200).json(om.writeValueAsString(newMessage));
        } else {
            context.status(400);
        }
    }

    private void deleteMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        int id = Integer.valueOf(context.pathParam("message_id"));
        Message message = messageService.deleteMessage(id);
        if(message != null){
            context.status(200).json(om.writeValueAsString(message));
        } else {
            context.status(200).json("");
        }
    }

    private void getMessageByIdHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        int id = Integer.valueOf(context.pathParam("message_id"));
        Message message = messageService.getMessageById(id);
        if(message != null){
            context.status(200).json(om.writeValueAsString(message));
        } else {
            context.status(200).json("");
        }
    }

    private void getMessagesHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        List<Message> messages = messageService.getAllMessages();
        context.status(200).json(om.writeValueAsString(messages));
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void postMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(context.body(), Message.class);
        Message newMsg = messageService.postMessage(message);
        if(newMsg != null){
            context.status(200).json(om.writeValueAsString(newMsg));
        } else {
            context.status(400);
        }
    }

    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account newAcc = accountService.loginAccount(account);
        if(newAcc != null){
            context.status(200).json(om.writeValueAsString(newAcc));
        } else {
            context.status(401);
        }
    }

    private void registerHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account newAcc = accountService.registerAccount(account);
        if(newAcc != null){
            context.status(200).json(om.writeValueAsString(newAcc));
        } else {
            context.status(400);
        }
    }


}