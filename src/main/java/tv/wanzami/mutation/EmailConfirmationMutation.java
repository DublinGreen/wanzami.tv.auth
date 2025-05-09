//package tv.wanzami.mutation;
//
//import java.util.Date;
//import java.util.Optional;
//import java.util.Random;
//
//import org.springframework.stereotype.Component;
//
//import graphql.kickstart.tools.GraphQLMutationResolver;
//import jakarta.persistence.EntityNotFoundException;
//import tv.wanzami.model.EmailConfirmation;
//import tv.wanzami.model.User;
//import tv.wanzami.repository.EmailConfirmationRepository;
//import tv.wanzami.repository.UserRepository;
//
///**
// * Email Confirmation Mutation
// */
//@Component
//public class EmailConfirmationMutation implements GraphQLMutationResolver {
//
//	private EmailConfirmationRepository emailConfirmationrepository;
//	private UserRepository userRepository;
//
//	public EmailConfirmationMutation(EmailConfirmationRepository emailConfirmationrepository) {
//		this.emailConfirmationrepository = emailConfirmationrepository;
//	}
//	
//	private String checkIfCodeCanBeUsed(Integer code, Long user_id) {
//	    String numberToString = Integer.toString(code);
//	    Optional<EmailConfirmation> optEmailConfirmation = emailConfirmationrepository.findByCode(numberToString);
//	    
//	    if(optEmailConfirmation.isPresent()) {
//	    	int temp = (int) (code + user_id);
//	    	return Integer.toString(temp);
//	    }else {
//	    	return numberToString;
//	    }	    
//	}
//	
//	private String generateCode(Long user_id) {
//	    Random rnd = new Random();
//	    int number = rnd.nextInt(999999);
//	    
//	    return this.checkIfCodeCanBeUsed(number, user_id);
//	}
//
//	public EmailConfirmation createEmailConfirmation(Long user_id, Integer status) {
//		EmailConfirmation emailConfirmation = new EmailConfirmation();
//		emailConfirmation.setStatus(status);
////		emailConfirmation.setUserId(user_id);	
//		emailConfirmation.setCode(this.generateCode(user_id));
//
//		emailConfirmationrepository.save(emailConfirmation);
//
//		return emailConfirmation;
//	}
//
//	public EmailConfirmation setActiveEmailConfirmationById(Long userId, Long emailConfirmationId) throws EntityNotFoundException {
//		Optional<EmailConfirmation> optEmailConfirmation = emailConfirmationrepository.findById(emailConfirmationId);
//		Optional<User> optUser = userRepository.findById(userId);
//		
//		if (optEmailConfirmation.isPresent() || optUser.isPresent()) {
//			EmailConfirmation emailConfirmation = optEmailConfirmation.get();
//			emailConfirmation.setStatus(1);
//			emailConfirmation.setUpdated_at(new Date().toInstant());
//
//			User user = optUser.get();
//			user.setStatus(1);
//			user.setUpdated_at(new Date().toInstant());
//
//
//			emailConfirmationrepository.save(emailConfirmation);
//			userRepository.save(user);
//			return emailConfirmation;
//		}
//
//		throw new EntityNotFoundException("Not found Email Confirmation to update!");
//	}
//
//}
