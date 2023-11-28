package main.java.interface_adapter.search_course;

import main.java.use_case.search_course.SearchCourseInputData;
import main.java.use_case.search_course.SearchCourseInputBoundary;

public class SearchCourseController {
    final SearchCourseInputBoundary searchUseCaseInteractor;
    public SearchCourseController(SearchCourseInputBoundary searchUseCaseInteractor){
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }
    public void execute(String coursecode, boolean searchForTutor, String userID){
        SearchCourseInputData searchCourseInputData = new SearchCourseInputData(coursecode, searchForTutor, userID);
        searchUseCaseInteractor.execute(searchCourseInputData);
    }
}
