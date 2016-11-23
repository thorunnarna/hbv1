package project.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import project.persistence.entities.ScheduleItem;
import project.service.ScheduleService;

/**
 * Created by Svava on 19.11.16.
 */
public class ItemValidator implements Validator {

    private ScheduleService scheduleService = new ScheduleService();

    @Override
    public boolean supports(Class<?> aClass) {
        return ScheduleItem.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ScheduleItem item = (ScheduleItem) o;

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
        if (item.getTitle().length() == 0 || item.getTitle().length() > 32) {
            errors.rejectValue("title", "Title can not be empty and length should be less than 32 characters");
        }

       
        if (item.getdate() == ""){
            errors.rejectValue("date","You must choose a date");
        }

        boolean retval = scheduleService.checkTime(item.getStartstring(), item.getEndstring());

        if (!retval) {
            errors.rejectValue("endTime", "End Time should be after Start Time");
        }

    }
}
