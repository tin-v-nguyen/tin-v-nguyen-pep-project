package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountService accountService;

    public MessageService() {
        messageDAO = new MessageDAO();
        accountService = new AccountService();
    }

    public MessageService(MessageDAO messageDAO, AccountService accountService) {
        this.accountService = accountService;
        this.messageDAO = messageDAO;
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message deleteMessage(int id) {
        return messageDAO.deleteMessage(id);
    }

    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message postMessage(Message message) {
        if (message.getMessage_text().equals("") 
        || message.getMessage_text().length() > 255 
        || accountService.findAccountById(message.posted_by) == null) return null;
        return messageDAO.insertMessage(message);
    }

}
