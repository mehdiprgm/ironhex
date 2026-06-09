package com.zen.ironhex.cli.commands;

import com.zen.ironhex.cli.commands.interfaces.Command;
import com.zen.ironhex.cli.parser.EntityResolver;
import com.zen.ironhex.domain.entity.main.*;
import com.zen.ironhex.domain.service.*;
import com.zen.ironhex.shared.Result;
import com.zen.lib.terminal.Table;
import com.zen.lib.terminal.drivers.LinuxTerminalDriver;

import java.util.List;

import static com.zen.ironhex.shared.Variables.*;
import static com.zen.lib.terminal.Terminal.*;
import static com.zen.lib.terminal.commands.CommandUtils.*;

public class ListCommand implements Command {

    private void showAccounts(int userId) throws Exception {
        AccountService service = new AccountService();
        List<Account> accounts = service.selectAll(userId);

        if (!accounts.isEmpty()) {
            Table table = new Table(new LinuxTerminalDriver());

            table.addColumn("id", "name", "username", "create date");
            table.setColumnsMaxSize(25);

            for (Account account : accounts) {
                table.addRow(account.getId(),
                        account.getName(),
                        account.getUsername(),
                        account.getCreateDate()
                );
            }

            println();
            println(table.render());
        }
    }

    private void showBankCards(int userId) throws Exception {
        BankCardService service = new BankCardService();
        List<Bankcard> bankcards = service.selectAll(userId);

        if (!bankcards.isEmpty()) {
            Table table = new Table(new LinuxTerminalDriver());

            table.addColumn("id", "name", "card number", "cvv2", "expire date");
            table.setColumnsMaxSize(25);

            for (Bankcard bankcard : bankcards) {
                table.addRow(
                        bankcard.getId(),
                        bankcard.getName(),
                        bankcard.getCardNumber(),
                        bankcard.getCvv2(),
                        bankcard.getExpireDate()
                );
            }

            println();
            println(table.render());
        }
    }

    private void showContacts(int userId) throws Exception {
        ContactService service = new ContactService();
        List<Contact> contacts = service.selectAll(userId);

        if (!contacts.isEmpty()) {
            Table table = new Table(new LinuxTerminalDriver());

            table.addColumn("id", "name", "phone number");
            table.setColumnsMaxSize(25);

            for (Contact contact : contacts) {
                table.addRow(
                        contact.getId(),
                        contact.getName(),
                        contact.getPhoneNumber()
                );
            }

            println();
            println(table.render());
        }
    }

    private void showNotes(int userId) throws Exception {
        NoteService service = new NoteService();
        List<Note> notes = service.selectAll(userId);

        if (!notes.isEmpty()) {
            Table table = new Table(new LinuxTerminalDriver());

            table.addColumn("id", "name");
            table.setColumnsMaxSize(25);

            for (Note note : notes) {
                table.addRow(
                        note.getId(),
                        note.getName()
                );
            }

            println();
            println(table.render());
        }
    }

    private void showPasswords(int userId) throws Exception {
        PasswordService service = new PasswordService();
        List<Password> passwords = service.selectAll(userId);

        if (!passwords.isEmpty()) {
            Table table = new Table(new LinuxTerminalDriver());

            table.addColumn("id", "name", "stength");
            table.setColumnsMaxSize(25);

            for (Password password : passwords) {
                table.addRow(
                        password.getId(),
                        password.getName(),
                        password.getStrength()
                );
            }

            println();
            println(table.render());
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
                    if (arguments.isEmpty()) {
                        int userId = Integer.parseInt(vars.get("user_id"));

                        switch (entity) {
                            case "--account" -> showAccounts(userId);
                            case "--card" -> showBankCards(userId);
                            case "--contact" -> showContacts(userId);
                            case "--note" -> showNotes(userId);
                            case "--pass" -> showPasswords(userId);
                        }
                    } else {
                        perror("error: this command takes only entity\n");
                    }
                }
            } else {
                perror("error: %s\n".formatted(result.message()));
            }
        } catch (Exception ex) {
            perror("error: %s\n", ex.getMessage());
        }
    }
}
