package Service;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountService

    public MessageService() {
        messageDAO = new MessageDAO();
        
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }
    public Message postMessage(Message message) {
        if (message.getMessage_text().equals("") 
        || message.getMessage_text().length() > 255 
        || findAccountById(account.getUsername()) != null) return null;
        return accountDAO.insertAccount(account);
    }

}
