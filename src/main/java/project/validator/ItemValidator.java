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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
        if (item.getTitle().length() == 0 || item.getTitle().length() > 32) {
            errors.rejectValue("title", "Title length should be less than 32 characters");
        }

        if (item.getEndTime().isBefore(item.getStartTime())) {
            errors.rejectValue("endTime", "End Time should be after Start Time");
        }

        if (item.getEndTime().getDayOfYear() != item.getStartTime().getDayOfYear()) {
            errors.rejectValue("endTime", "End Time and STart Time should be on the same day");
        }
    }
}
