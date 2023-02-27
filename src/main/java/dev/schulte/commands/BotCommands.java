package dev.schulte.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class BotCommands extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(event.getChannel().getId().equals("1078291437399064698")){

            if(event.getName().equals("bruh")){

                event.deferReply().queue();


            }


        }else{

            if(event.getName().equals("fart")){

                event.deferReply().queue();

                event.getHook().sendMessage("You just farted.").setEphemeral(true).queue();

            }else if(event.getName().equals("food")){

                OptionMapping option = event.getOption("name");

                if(option == null){
                    event.reply("for some reason, a food name was not provided.").queue();

                    return;
                }

                String favoriteFood = option.getAsString();

                event.reply("Your favorite food is: " + favoriteFood).queue();

            }else if(event.getName().equals("sum")){

                OptionMapping operand1 = event.getOption("operand1");
                OptionMapping operand2 = event.getOption("operand2");

                if(operand1 == null || operand2 == null){
                    event.reply("No numbers were provided.").queue();

                    return;
                }

                int sum = operand1.getAsInt() + operand2.getAsInt();

                event.reply("The sum is: " + sum).queue();

            }
        }
    }
}
