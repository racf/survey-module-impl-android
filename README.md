# Welcome to the SurveyModule implementation

***SurveyModule is a module that allows you to apply surveys. This module returns the JSON of the survey with your responses.***

## Minimum SDK Version

    Minimum version 16 (Android 4.1 - Jelly Bean)

## Features
- The answer of a certain question (RadioGroup, Checkbox, Select) can display a set of questions.
- The answer of a certain question (RadioGroup, Checkbox, Select) can hide a set of questions.
- The answer to a certain question can show a set of answers to another question.
- The answer to a certain question can end the survey.
- The questionnaire is concluded until the mandatory questions are answered.
- If you return to a questionnaire, the previous data is loaded.
- The survey is updated if the value of the versionCode attribute changes.

## Download this project

    git clone https://github.com/racf/survey-module-impl-android.git

## Import to a project
1. From the menu bar, click **File** -> **Import module...**
2. Navigate to the **surveymoduleimplandroid**
3. Select the **surveymodule** and click **Finish**
4. From the menu bar, click **File** -> **Project Structure...**
5. Select the menu **Dependencies**
6. Select the **app** folder
7. Click on the **add dependency** button (+)
8. Click the menu **module dependency**
9. Select the module **surveymodule** and click **Ok**
10. Click **Apply** and click **Ok**

The image shows the **surveymodule** in the project dependencies:
![SurveyModule](module-dependency.png)

## Implementation in a project
1. Generate an xml file to design a CardView. This CardView shows the survey to be applied. This file is in the demo at the following location: [cards_surveys_layout.xml](https://github.com/racf/survey-module-impl-android/blob/master/app/src/main/res/layout/cards_surveys_layout.xml)
2. Add the RecyclerView in the [activity_main.xml](https://github.com/racf/survey-module-impl-android/blob/master/app/src/main/res/layout/activity_main.xml)
    ```xml
        <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerViewSurveys"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginStart="@dimen/margin_padding_8"
        android:layout_marginEnd="@dimen/margin_padding_8"
        android:layout_marginTop="@dimen/margin_padding_8"
        android:scrollbars="vertical"/>
    ```
3. Create an instance of the SurveyService in the [MainActivity.java](https://github.com/racf/survey-module-impl-android/blob/master/app/src/main/java/com/cysout/sousystems/survey/impl/MainActivity.java)
```java
    SurveyService surveyService = new ViewModelProvider(this).get(SurveyServiceImpl.class);
```
4. Save the surveys in SurveyModulo with the **saveSurveys(surveys)** method. ***saveSurveys(surveys)*** receives as a parameter a json (JSON Array) with a set of surveys. Surveys can come from any data source (REST API). SurveyModule has sample surveys in **jsonArrayTest()** method.
```java
    String surveys = Utils.jsonArrayTest();
    surveyService.saveSurveys(surveys);
```
5. Show the surveys with the **loadAllSurveys()** method. The following code snippet shows the example.
```java
    surveyService.loadAllSurveys().observe(this, new Observer<List<Survey>>() {
        @Override
        public void onChanged(List<Survey> surveys) {
            adapter = new SurveyAdapter(surveys, R.layout.cards_surveys_layout, new SurveyAdapter.CustomHolder.OnItemClickListener() {
                @Override
                public void onItemClick(Survey survey, int position) {

                }

                @Override
                public void btnOnClick(View v, Survey survey, int position) {
                    surveyService.startNewSurvey(getApplicationContext(), activity, survey);
                }
            });
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    });
```
6. The **startNewSurvey(Context context, Activity activity, Survey survey)** method starts a new survey. This method receives as parameters: the context of the application, the activity and the survey. This method is implemented in the **btnOnClick** from step 5.
```java
    surveyService.startNewSurvey(getApplicationContext(), activity, survey);
```
7. The result of the survey is obtained in the **onActivityResult** method. The following code snippet shows the example.
```java
   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CustomConstants.QUESTIONNAIRES_REQUEST) {
            if (resultCode == RESULT_OK) {
                String surveyResponses = data.getStringExtra(CustomConstants.SURVEY_RESPONSE);
                Log.i(CustomConstants.TAG_LOG.concat(" RESULT "), surveyResponses);
                Toast.makeText(this, getString(R.string.message_finished_survey), Toast.LENGTH_LONG).show();
            }
        }
    }
```
## Component types
1. **Text** : This type of component allows capturing different types of text in an input. The supported input types are the following:

    - phone
    - textPersonName
    - number
    - text

2. **RadioGroup** : This type of component allows you to select a single answer from a group of answers.
3. **Checkbox** : This type of component allows you to select one or more answers from a group of answers.
4. **Select** : This type of component allows you to select a single option from a list of options.
5. **Label** : This type of component allows you to add notices, descriptions or some indication.

## License
SurveyModule is released under the [Apache 2.0 license](LICENSE).
```
   Copyright 2021 CysOut Solutions and SouSystems

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

```

## **Join us to improve SurveyModule!** :page_with_curl: