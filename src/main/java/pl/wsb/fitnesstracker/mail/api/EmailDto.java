package pl.wsb.fitnesstracker.mail.api;

/**
 * Representation of Email object
 * @param toAddress
 * @param subject
 * @param content
 */
public record EmailDto(String toAddress, String subject, String content) {

}
