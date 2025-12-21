package pl.wsb.fitnesstracker.mail.api;

/**
 * Representation of Email object
 * @param toAddress email address
 * @param subject subject of the email
 * @param content content of the email
 */
public record EmailDto(String toAddress, String subject, String content) {

}
