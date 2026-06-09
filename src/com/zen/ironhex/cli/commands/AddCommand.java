package com.zen.ironhex.cli.commands;

import com.zen.ironhex.cli.commands.interfaces.Command;
import com.zen.ironhex.cli.parser.EntityResolver;
import com.zen.ironhex.domain.entity.main.*;
import com.zen.ironhex.domain.service.*;
import com.zen.ironhex.shared.Result;
import com.zen.lib.system.software.OS;
import me.gosimple.nbvcxz.Nbvcxz;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

import static com.zen.ironhex.shared.Variables.*;
import static com.zen.lib.terminal.Terminal.*;
import static com.zen.lib.terminal.commands.CommandUtils.*;

public class AddCommand implements Command {

    private void addAccount(List<String> arguments, String name, int userId) throws Exception {
        String username = findArgument(arguments, "-u"),
                password = findArgument(arguments, "-p"),
                extraInfo = findArgument(arguments, "-x");

        List<Pair<String, String>> items = List.of(
                Pair.of("name", name),
                Pair.of("username", username),
                Pair.of("password", password)
        );

        if (isArgumentsValuesAvailable(items)) {
            AccountService accountService = new AccountService();

            if (accountService.exists(userId, name)) {
                printf("message: name already exists in the database\n");
            } else {
                Account account = new Account(
                        userId, name, username, password,
                        extraInfo, OS.getTimeDate(OS.SystemTimeDate.DATE)
                );

                Result result = accountService.insert(account);
                if (!result.success()) {
                    perror("error: %s\n", result.message());
                }
            }
        }
    }

    private void addCard(List<String> arguments, String name, int userId) throws Exception {
        String cardNumber = findArgument(arguments, "-c"),
                accountNumber = findArgument(arguments, "-a"),
                cvv2 = findArgument(arguments, "-v"),
                expireDate = findArgument(arguments, "-e"),
                password = findArgument(arguments, "-p");

        List<Pair<String, String>> item = List.of(
                Pair.of("name", name),
                Pair.of("card number", cardNumber),
                Pair.of("cvv2", cvv2),
                Pair.of("expire date", expireDate),
                Pair.of("password", password)
        );

        if (isArgumentsValuesAvailable(item)) {
            BankCardService bankCardService = new BankCardService();

            if (bankCardService.exists(userId, name)) {
                printf("message: name already exists in the database\n");
            } else {
                Bankcard bankcard = new Bankcard(
                        userId, name, cardNumber, accountNumber,
                        cvv2, expireDate, password, OS.getTimeDate(OS.SystemTimeDate.DATE)
                );

                Result result = bankCardService.insert(bankcard);
                if (!result.success()) {
                    perror("error: %s\n", result.message());
                }
            }
        }
    }

    private void addContact(List<String> arguments, String name, int userId) throws Exception {
        String phoneNumber = findArgument(arguments, "-p"),
                extraInfo = findArgument(arguments, "-x");

        List<Pair<String, String>> items = List.of(
                Pair.of("name", name),
                Pair.of("phone number", phoneNumber)
        );

        if (isArgumentsValuesAvailable(items)) {
            ContactService contactService = new ContactService();

            if (contactService.exists(userId, name)) {
                printf("message: name already exists in the database\n");
            } else {
                Contact contact = new Contact(
                        userId, name, phoneNumber, extraInfo,
                        OS.getTimeDate(OS.SystemTimeDate.DATE)
                );

                Result result = contactService.insert(contact);
                if (!result.success()) {
                    perror("error: %s\n", result.message());
                }
            }
        }
    }

    private void addNote(List<String> arguments, String name, int userId) throws Exception {
        String content = findArgument(arguments, "-c");
        List<Pair<String, String>> items = List.of(
                Pair.of("name", name)
        );

        if (isArgumentsValuesAvailable(items)) {
            NoteService noteService = new NoteService();

            if (noteService.exists(userId, name)) {
                printf("message: name already exists in the database\n");
            } else {
                Note note = new Note(
                        userId, name, content, OS.getTimeDate(OS.SystemTimeDate.DATE)
                );

                Result result = noteService.insert(note);
                if (!result.success()) {
                    perror("error: %s\n", result.message());
                }
            }
        }
    }

    private void addPassword(List<String> arguments, String name, int userId) throws Exception {
        String password = findArgument(arguments, "-p");
        List<Pair<String, String>> items = List.of(
                Pair.of("name", name),
                Pair.of("password", password)
        );

        if (isArgumentsValuesAvailable(items)) {
            PasswordService passwordService = new PasswordService();

            if (passwordService.exists(userId, name)) {
                printf("message: name already exists in the database\n");
            } else {
                Nbvcxz nbvcxz = new Nbvcxz();

                Password entity = new Password(
                        userId, name, password, String.valueOf(password.length()),
                        nbvcxz.estimate(password).getBasicScore(), OS.getTimeDate(OS.SystemTimeDate.DATE)
                );

                Result result = passwordService.insert(entity);
                if (!result.success()) {
                    perror("error: %s\n", result.message());
                }
            }
        }
    }

    @Override
    public void execute(List<String> arguments) {
        try {
            EntityResolver resolver = new EntityResolver();
            Result result = resolver.searchEntity(arguments, true);

            if (result.success()) {
                String entity = result.message();

                if (entity == null) {
                    perror("error: no entity found\n");
                } else {
                    if (isArgumentsSizeValid(arguments, entitiesMaxSize.get(entity))) {
                        String name = findArgument(arguments, "-n");
                        int userId = Integer.parseInt(vars.get("user_id"));

                        switch (entity) {
                            case "--account" -> addAccount(arguments, name, userId);
                            case "--card" -> addCard(arguments, name, userId);
                            case "--contact" -> addContact(arguments, name, userId);
                            case "--note" -> addNote(arguments, name, userId);
                            case "--pass" -> addPassword(arguments, name, userId);
                        }
                    }
                }
            } else {
                perror("error: %s\n".formatted(result.message()));
            }
        } catch (Exception ex) {
            perror("error: %s\n", ex.getMessage());
        }
    }

    private boolean isArgumentsValuesAvailable(List<Pair<String, String>> items) {
        for (Pair<String, String> item : items) {
            if (item.getValue() == null || item.getValue().trim().isBlank()) {
                perror("error: no %s found\n".formatted(item.getKey()));
                return false;
            }
        }

        return true;
    }
}
