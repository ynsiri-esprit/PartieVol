package Services;

import Entities.Agent;

import javax.mail.MessagingException;

public interface IAgentService extends IService<Agent>{
    public void sendEmailToAgent(Agent agent) throws MessagingException;
}
