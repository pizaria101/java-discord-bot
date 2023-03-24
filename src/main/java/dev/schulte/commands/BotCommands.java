package dev.schulte.commands;

import dev.schulte.daos.BruhDAO;
import dev.schulte.daos.BruhDAOPostgres;
import dev.schulte.entities.Bruh;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

public class BotCommands extends ListenerAdapter {

    static BruhDAO bruhDAO = new BruhDAOPostgres();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(event.getChannel().getId().equals("1078291437399064698")){

            if(event.getName().equals("bruh")){
                String id = event.getUser().getId();

                if(bruhDAO.getBruhByUserId(id) == null){
                    long bruhMoments = randomBruhGenerator();
                    long time = Instant.now().getEpochSecond();
                    Bruh bruh = new Bruh(id, bruhMoments, time);
                    bruhDAO.createBruh(bruh);
                    event.reply("You just experienced " + bruhMoments + " bruh moments.").queue();

                }else if(bruhDAO.getBruhByUserId(id) != null){
                    Bruh bruh = bruhDAO.getBruhByUserId(id);
                    long time = bruh.getTime();
                    long current = Instant.now().getEpochSecond();
                    long elapsed = current - time;

                    if(elapsed < 14400L){
                        Duration duration = Duration.ofSeconds(14400L-elapsed);
                        String formatted = String.format("%dhrs %02dmins", duration.toHours(), duration.toMinutesPart());
                        event.reply("You gotta wait " + formatted + ".").queue();

                    }else{
                        long bruhMoments = randomBruhGenerator();
                        bruh.setBruhMoment(bruhMoments + bruh.getBruhMoment());
                        bruh.setTime(current);
                        bruhDAO.updateBruh(bruh);
                        event.reply("You just experienced " + bruhMoments + " bruh moments.").queue();
                    }
                }

            }else{
                event.reply("That can't be used here, you silly goose.").queue();
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

    public long randomBruhGenerator(){

        Random rand = new Random();
        return rand.nextLong(5000);

    }
}
