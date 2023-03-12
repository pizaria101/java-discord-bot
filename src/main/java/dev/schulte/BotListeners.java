package dev.schulte;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.entities.emoji.EmojiUnion;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotListeners extends ListenerAdapter {

//    @Override
//    public void onMessageReceived(MessageReceivedEvent event) {
//
//        if(!event.getAuthor().isBot()){
//            String messageSent = event.getMessage().getContentRaw();
//            event.getChannel().sendMessage("This was sent: " + messageSent).queue();
//        }
//
//    }

    @Override
    public void onChannelDelete(ChannelDeleteEvent event) {

        String channelName = event.getChannel().getName();

        TextChannel general = event.getGuild().getTextChannelById(1047256709950865440L);

        if(general != null){
            general.sendMessage("The channel: \"" + channelName + "\" was deleted.").queue();
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {

        if(!event.getUser().isBot()){

            String message = event.getMessageId();
            event.getChannel().addReactionById(message, Emoji.fromUnicode("U+1F1EB")).queue();
        }
    }
}
