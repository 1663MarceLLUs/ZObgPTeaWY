// 代码生成时间: 2025-10-04 03:11:27
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Path("/bank")
public class DigitalBankPlatform {
    // A map to simulate a database of accounts
    private Map<Long, Account> accounts = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    // Represents an account with basic properties
    public static class Account {
        private final long id;
        private double balance;

        public Account(double initialBalance) {
            this.id = nextId.getAndIncrement();
            this.balance = initialBalance;
        }

        public long getId() {
            return this.id;
        }

        public double getBalance() {
            return this.balance;
        }

        public void deposit(double amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("Deposit amount must be positive.");
            }
            this.balance += amount;
        }

        public void withdraw(double amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive.");
            }
            if (this.balance < amount) {
                throw new InsufficientFundsException("Insufficient funds for withdrawal.");
            }
            this.balance -= amount;
        }
    }

    // Custom exception for insufficient funds
    public static class InsufficientFundsException extends Exception {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

    // Endpoint to create a new account with an initial balance
    @POST
    @Path("/accounts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(double initialBalance) {
        if (initialBalance < 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Initial balance cannot be negative.").build();
        }

        Account newAccount = new Account(initialBalance);
        accounts.put(newAccount.getId(), newAccount);
        return Response.status(Response.Status.CREATED).entity(newAccount).build();
    }

    // Endpoint to deposit money into an account
    @POST
    @Path("/accounts/{id}/deposit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deposit(@PathParam("id") long id, double amount) {
        Account account = accounts.get(id);
        if (account == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Account not found.").build();
        }

        try {
            account.deposit(amount);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        return Response.status(Response.Status.OK).entity(account).build();
    }

    // Endpoint to withdraw money from an account
    @POST
    @Path("/accounts/{id}/withdraw")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response withdraw(@PathParam("id") long id, double amount) {
        Account account = accounts.get(id);
        if (account == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Account not found.").build();
        }

        try {
            account.withdraw(amount);
        } catch (IllegalArgumentException | InsufficientFundsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        return Response.status(Response.Status.OK).entity(account).build();
    }
}
