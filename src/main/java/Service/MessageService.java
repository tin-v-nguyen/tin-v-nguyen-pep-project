package Service;

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
    public Message postMessage(Message message) {
        if (message.getMessage_text().equals("") 
        || message.getMessage_text().length() > 255 
        || accountService.findAccountById(message.posted_by) == null) return null;
        return messageDAO.insertMessage(message);
    }

}
