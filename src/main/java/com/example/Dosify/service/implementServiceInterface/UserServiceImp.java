package com.example.Dosify.service.implementServiceInterface;

import com.example.Dosify.Enum.Gender;

import com.example.Dosify.dto.RequestDto.UserRequestDto;
import com.example.Dosify.dto.ResponseDto.UserResponseDto;
import com.example.Dosify.exception.UserNotFoundException;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.UserService;
import com.example.Dosify.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        /*Convert request DTO into entity*/

//        User user = new User();
//        user.setName(userRequestDto.getName());
//        user.setAge(userRequestDto.getAge());
//        user.setEmailId(userRequestDto.getEmailId());
//        user.setMobNo(userRequestDto.getMobNo());
//        user.setGender(userRequestDto.getGender());

        /*Set the parameter using @Builder class*/

        User user = UserTransformer.UserRequestDtoToUser(userRequestDto);

        /*Save the object in database*/
        userRepository.save(user);

        /*Convert entity into response DTO*/

//        UserResponseDto userResponseDto = new UserResponseDto();
//        userResponseDto.setName(user.getName());
//        userResponseDto.setMessage("Congrats! You have registered on Dosify");


        userRegistrationMAil(user);

        return UserTransformer.UserToUserResponseDto(user);
    }

    private void userRegistrationMAil(User user){
        String text = "Congrats!! \n" + user.getName() +
                " has been successfully registered on Dosify COVID_19 Vaccination Center! \n\n" +
                "Thank you!!!" + "\n" + "no-reply this is automated generated mail.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ecommerce7232@gmail.com");
        message.setTo(user.getEmailId());
        message.setSubject("Dosify COVID_19 Vaccination Center!!!");
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void updateUser(String emailId, String name, String mobNo) throws UserNotFoundException {
        User user = userRepository.findByEmailId(emailId);
        if (user == null){
            throw new UserNotFoundException("User doesn't exist.");
        }
        user.setName(name);
        user.setMobNo(mobNo);
        userRepository.save(user);
    }

    @Override
    public List<String> userNotTakenEvenASingleDose() {
        return userRepository.getUserNotTakenEvenASingleDose();
    }

    @Override
    public List<String> userTakenDose1NotTakenDose2() {
        return userRepository.getUserTakenDose1NotTakenDose2();
    }

    @Override
    public List<String> allUserFullyVaccinated() {
        return userRepository.getAllUserFullyVaccinated();
    }

    @Override
    public List<String> maleUserNotTakenEvenASingleDose(String male) {
        return userRepository.getMaleUserNotTakenEvenASingleDose(male);
    }

    @Override
    public List<String> femaleUserNotTakenEvenASingleDose(String female) {
        return userRepository.getFemaleUserNotTakenEvenASingleDose(female);
    }

    @Override
    public List<String> allMaleUserFullyVaccinated(String male) {
        return userRepository.getAllMaleUserFullyVaccinated(male);
    }

    @Override
    public List<String> allFemaleUserFullyVaccinated(String female) {
        return userRepository.getAllFemaleUserFullyVaccinated(female);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
