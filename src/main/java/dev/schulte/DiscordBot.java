package dev.schulte;

import dev.schulte.commands.BotCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import javax.security.auth.login.LoginException;

public class DiscordBot extends ListenerAdapter {

    public static void main(String[] args) throws LoginException, InterruptedException {

        JDA jda = JDABuilder.createDefault(System.getenv("DiscordBot"))
                .setActivity(Activity.listening("that sweet music"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new BotCommands())
                .addEventListeners(new BotListeners())
                .build().awaitReady();

        //Global Commands and Guild Commands
        //Global Commands - they can be used anywhere: any guild that your bot is in and also in DMs
        //Guild Commands - they can only be used in a specific guild

        //For global, do it on jda instead of guild, will take up to an hour to register

        Guild guild = jda.getGuildById("1047256708898115726");

        if(guild != null){
            guild.upsertCommand("fart", "Fart really hard").queue();
            guild.upsertCommand("food", "Name your favorite food")
                    .addOption(OptionType.STRING, "name", "the name of your favorite food", true)
                    .queue();
            guild.upsertCommand("sum", "Add two numbers together")
                    .addOptions(new OptionData(OptionType.INTEGER, "operand1", "the first number", true)
                                    .setRequiredRange(1, Integer.MAX_VALUE),
                            new OptionData(OptionType.INTEGER, "operand2", "the second number", true)
                                    .setRequiredRange(1, Integer.MAX_VALUE))
                    .queue();
        }

//        CommandListUpdateAction commands = jda.updateCommands();
//
//        commands.addCommands(
//                Commands.slash("fart", "Fart really hard"),
//                Commands.slash("food", "Name your favorite food")
//                        .addOption(OptionType.STRING, "name", "the name of your favorite food", true),
//                Commands.slash("sum", "Add two numbers together")
//                        .addOptions(new OptionData(OptionType.INTEGER, "operand1", "the first number", true)
//                                        .setRequiredRange(1, Integer.MAX_VALUE),
//                                new OptionData(OptionType.INTEGER, "operand2", "the second number", true)
//                                        .setRequiredRange(1, Integer.MAX_VALUE))
//        );
//        commands.queue();

    }


}
