package net.rhuanrocha.samplecdi.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Objects;

@ConversationScoped
public class ShoppingCart implements Serializable {

    @Inject
    private Conversation conversation;

    private Long itemNumber;

    @PostConstruct
    public void init(){
        this.itemNumber = 0L;
    }

    public void increaseItemNumber(){
        this.itemNumber ++;
    }

    public Long getItemNumber(){
        return this.itemNumber;
    }

    public void startConversation(){
        if(conversation.isTransient()) {
            conversation.begin();
        }

    }

    public void endConversation(){
        if(!conversation.isTransient()){
            conversation.end();
        }

    }

    public String getConversationId(){
        return conversation.getId();
    }


}
