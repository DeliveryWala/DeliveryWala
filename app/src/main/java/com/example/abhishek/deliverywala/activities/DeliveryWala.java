package com.example.abhishek.deliverywala.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.abhishek.deliverywala.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abhishek on 06-05-2016.
 */
public class DeliveryWala extends BaseActivity {


/*

        private int deeplinkId = R.id.HomePage;
        private String extraParam1 = null;
        private String extraParam2 = null;

        public interface JobApplyListener {
            public void onApplied();
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            initLeftMenuDrawer();

            changeFragment(R.id.HomePage);
            Intent intent = getIntent();
            if(intent != null) {
                deeplinkId = intent.getIntExtra(Constants.DEEPLINK_TAG, R.id.HomePage);
                extraParam1 = intent.getStringExtra(Constants.DEEPLINKING_EXTRA_PARAM1);
                extraParam2 = intent.getStringExtra(Constants.DEEPLINKING_EXTRA_PARAM2);
            }

            if(deeplinkId != R.id.HomePage)
                changeFragment(deeplinkId, extraParam1, extraParam2);
        }

        private void initLeftMenuDrawer() {
            mLeftDrawerView.addView(new LeftDrawerView(this, null).getPopulatedView(null, null, mLeftDrawerView));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    if (mDrawerToggle.isDrawerIndicatorEnabled()) {
                        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                            mDrawerLayout.closeDrawers();
                        } else {
                            if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT))
                                mDrawerLayout.closeDrawers();
                            mDrawerLayout.openDrawer(Gravity.LEFT);
                        }
                    } else {
                        onBackPressed();
                    }
            }
            return super.onOptionsItemSelected(item);
        }

        public void changeFragment(int menuId) {
            changeFragment(menuId, null, null);
        }

        public void changeFragment(int menuId, String extraParam1, String extraParam2) {
            changeFragment(menuId,extraParam1, extraParam2, null);
        }

        public void changeFragment(int menuId, String extraParam1, String extraParam2, BusinessObject businessObj) {
            URLManager urlManager = null;
            switch (menuId) {
                case R.id.HomePage:
                case R.id.YStoriesPage:
                case R.id.JobsPage:
                case R.id.DiscussionsPage:

                    if(! (mFragment instanceof HomeFragment)) {
                        HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setSelectedPageId(menuId);
                        changeFragment(homeFragment, null, true);
                    }else {
                        ((HomeFragment)mFragment).setSelectedPageId(menuId);
                        closeDrawer();
                    }
                    break;

                case R.id.ProfilePage:
                    //ProfileFragment profileFragment = new ProfileFragment();
                    //changeFragment(profileFragment);
                    break;
                case R.id.TestsPage:
                    urlManager = new URLManager(this);
                    urlManager.setBaseUrl(UrlConstants.GET_YTEST_CATEGORIES_URL);
                    urlManager.setClassName(TestCategories.class);
                    urlManager.setLoadMoreEnabled(true);
                    urlManager.setSetShouldCache(true);
                    urlManager.setRefresh(false);
                    urlManager.setUserSpecific(true);
                    urlManager.setNoDataText(getResources().getString(R.string.no_data_found_test));
                    urlManager.setActionbarTitle(Constants.ACTION_BAR_TITLE_YTEST);

                    ListingFragment testsFragment = new ListingFragment();
                    testsFragment.setUrlManager(urlManager);
                    changeFragment(testsFragment);
                    break;
                case R.id.JobDetailPage:
                    JobsDetailFragment jobsDetailFragment = new JobsDetailFragment();
                    if(businessObj == null && !TextUtils.isEmpty(extraParam1)) {
                        Jobs.Job job = new Jobs().new Job();
                        job.setJobId(extraParam1);
                        jobsDetailFragment.setJob(job);
                    }
                    else {
                        jobsDetailFragment.setJob((Jobs.Job) businessObj);
                    }
                    changeFragment(jobsDetailFragment);
                    break;
                case R.id.DiscussionDetailPage:
                    DiscussionsDetailFragment discussionsDetailFragment = new DiscussionsDetailFragment();
                    if(businessObj == null) {
                        Discussions.Discussion discussion = new Discussions().new Discussion();
                        discussion.setForumId(extraParam1);
                        discussionsDetailFragment.setDiscussion(discussion);
                    }
                    else
                        discussionsDetailFragment.setDiscussion((Discussions.Discussion)businessObj);
                    changeFragment(discussionsDetailFragment);
                    break;
                case R.id.CommentPage:
                    CommentFragment commentFragment = new CommentFragment();
                    commentFragment.setBusinessObject(businessObj);
                    changeFragment(commentFragment);
                    break;

                case R.id.AnswersDetailPage:
                    AnswersDetailFragment answersDetailFragment = new AnswersDetailFragment();
                    answersDetailFragment.setBusinessObject(businessObj);
                    changeFragment(answersDetailFragment);
                    break;

                case R.id.NotificationPage:
                    NotificationFragment notificationFragment = new NotificationFragment();
                    changeFragment(notificationFragment);
                    break;

                case R.id.MessagePage:
                    MessagesFragment messagesFragment = new MessagesFragment();
                    changeFragment(messagesFragment);
                    break;

                case R.id.ConversationPage:
                    if(! (mFragment instanceof ConversationsFragment) ){
                        ConversationsFragment conversationsFragment = new ConversationsFragment();
                        if(null != businessObj)
                            conversationsFragment.setMessageSource((Message) businessObj);
                        else {
                            Message message = new Message();
                            message.setSenderUserId(extraParam1);
                            conversationsFragment.setMessageSource(message);
                        }
                        changeFragment(conversationsFragment);
                    }
                    break;
                case R.id.YStoryDetailPage:
                    YStoryDetailFragment yStoryDetailFragment = new YStoryDetailFragment();
                    yStoryDetailFragment.setArrListYStories(((YStories)businessObj).getArrListBusinessObject());
                    yStoryDetailFragment.setClickedPosition(Integer.parseInt(extraParam1));
                    changeFragment(yStoryDetailFragment);
                    break;
                case R.id.TestsSubPage:
                    String subCategoryId = null;
                    String actionBarTitle = null;
                    urlManager = new URLManager(this);

                    if(businessObj != null ) {
                        subCategoryId = ((TestCategories.TestCategory) businessObj).getCatid();
                        actionBarTitle = ((TestCategories.TestCategory)businessObj).getCategory();
                    } else {
                        subCategoryId = extraParam1;
                        actionBarTitle = getResources().getString(R.string.left_menu_tests);
                    }

                    urlManager.setBaseUrl(UrlConstants.GET_YTEST_BY_CATEGORY_URL.replace("{CatId}", subCategoryId));
                    urlManager.setActionbarTitle(actionBarTitle);
                    urlManager.setClassName(TestSubCategories.class);
                    urlManager.setLoadMoreEnabled(true);
                    urlManager.setSetShouldCache(true);
                    urlManager.setUserSpecific(true);
                    urlManager.setRefresh(false);
                    urlManager.setNoDataText(getResources().getString(R.string.no_data_found_test));

                    ListingFragment testsSubFragment = new ListingFragment();
                    testsSubFragment.setUrlManager(urlManager);
                    changeFragment(testsSubFragment);
                    break;

                case R.id.TakeTestPage:
                    if(businessObj != null)
                        startTest(businessObj);
                    else {
                        TestSubCategories.TestSubCategory test = new TestSubCategories().new TestSubCategory();
                        test.setTestid(extraParam1);
                        startTest(test);
                    }
                    break;
            }
        }

        private void changeFragment(Fragment fragment){
            changeFragment(fragment, null);
        }

        private void changeFragment(Fragment fragment,String tag){
            changeFragment(fragment, tag, false);
        }

        public void applyJob(String jobId, final JobApplyListener listener) {
            String jobApplyUrl = UrlConstants.JOB_APPLY_URL
                    .replace("{jobid}", String.valueOf(jobId))
                    .replace("{userid}", String.valueOf(UserManager.getInstance().getCurrentUser().getUserId()));

            showProgressDialog(true, "Applying job...");
            FeedParams postFeedParams = new FeedParams(jobApplyUrl, JobApplyResult.class, new Interfaces.IDataRetrievalListener() {
                @Override
                public void onDataRetrieved(BusinessObject businessObject) {
                    hideProgressDialog();
                    boolean isJobApplied = false;
                    if(null != businessObject && businessObject.getVolleyError() == null && businessObject instanceof JobApplyResult) {
                        if(!"0".equalsIgnoreCase(((JobApplyResult)businessObject).getApplyJobResult()))
                            isJobApplied = true;
                    }

                    if(isJobApplied) {
                        Toast.makeText(YouthActivity.this, "Job applied successfully", Toast.LENGTH_SHORT).show();
                        if(null != listener)
                            listener.onApplied();
                    } else
                        Toast.makeText(YouthActivity.this, "You have already applied for this Job", Toast.LENGTH_SHORT).show();


                }
            });
            Map<String, String> postParams = new HashMap<>();
            postParams.put(UrlConstants.KEY_PARAMETER_POST, "");
            postFeedParams.setMethod(Request.Method.POST);
            postFeedParams.setPostParams(postParams);
            postFeedParams.setShouldCache(false);
            FeedManager.getInstance().queueJob(postFeedParams);
        }

        public void postCommentOnAnswer(BusinessObject businessObject, String answer) {
            if(businessObject instanceof Answers.Answer) {
                User currentUser = UserManager.getInstance().getCurrentUser();
                if (currentUser != null) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.accumulate(UrlConstants.KEY_COMMENT, answer);
                        showProgressDialog(false, "Posting your comment...");
                        String commentOnTalentForumUrl = UrlConstants.COMMENT_ON_TALENT_FORUM;
                        commentOnTalentForumUrl = commentOnTalentForumUrl.replace("{CommentByUserId}", currentUser.getUserId()).replace("{AnswerId}", ((Answers.Answer) businessObject).getAnswerId());

                        FeedParams feedParams = new FeedParams(commentOnTalentForumUrl, String.class, null);
                        feedParams.setMethod(Request.Method.POST);
                        feedParams.setShouldCache(false);
                        FeedManager.getInstance().queueJobMultipart(feedParams, jsonObject.toString(), new Interfaces.IDataRetrievalListenerString() {
                            @Override
                            public void onDataRetrieved(String string) {
                                hideProgressDialog();
                                if (!TextUtils.isEmpty(string)) {
                                    try {
                                        string = string.replaceAll("\"", "");
                                        if (Integer.parseInt(string) > 0) {
                                            Toast.makeText(YouthActivity.this, getResources().getString(R.string.comment_posted_successful), Toast.LENGTH_SHORT).show();
                                            onBackPressed();
                                        }
                                    } catch (Exception e) {
                                        Toast.makeText(YouthActivity.this, getResources().getString(R.string.comment_posted_unsuccessful), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(YouthActivity.this, getResources().getString(R.string.comment_posted_unsuccessful), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (Exception e) {
                    }
                }
            }
        }

        public void postAnswerOnForum(BusinessObject businessObject, String answerText) {
            if(businessObject instanceof Discussions.Discussion) {
                User currentUser = UserManager.getInstance().getCurrentUser();
                if (currentUser != null) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.accumulate(UrlConstants.KEY_ANSWER, answerText);
                        showProgressDialog(false, "Posting your answer...");
                        String postTalentForumAnswerUrl = UrlConstants.POST_TALENT_FORUM_ANSWER;
                        Discussions.Discussion discussion = (Discussions.Discussion) businessObject;
                        postTalentForumAnswerUrl = postTalentForumAnswerUrl
                                .replace("{AnswerByUserId}", currentUser.getUserId())
                                .replace("{ForumId}", discussion.getForumId())
                                .replace("{usertype}", currentUser.getUserType());

                        FeedParams feedParams = new FeedParams(postTalentForumAnswerUrl, String.class, null);
                        feedParams.setMethod(Request.Method.POST);
                        feedParams.setShouldCache(false);
                        FeedManager.getInstance().queueJobMultipart(feedParams, jsonObject.toString(), new Interfaces.IDataRetrievalListenerString() {
                            @Override
                            public void onDataRetrieved(String string) {
                                hideProgressDialog();
                                if (!TextUtils.isEmpty(string)) {
                                    try {
                                        string = string.replaceAll("\"", "");
                                        if (Integer.parseInt(string) > 0) {
                                            Toast.makeText(YouthActivity.this, getResources().getString(R.string.answer_posted_successful), Toast.LENGTH_SHORT).show();
                                            onBackPressed();
                                        }
                                    } catch (Exception e) {
                                        Toast.makeText(YouthActivity.this, getResources().getString(R.string.answer_posted_unsuccessful), Toast.LENGTH_SHORT).show();
                                        onBackPressed();
                                    }
                                } else {
                                    Toast.makeText(YouthActivity.this, getResources().getString(R.string.answer_posted_unsuccessful), Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                }
                            }
                        });
                    } catch (Exception e) {
                    }
                }
            } else if(businessObject instanceof YStories.YStory) {
                User currentUser = UserManager.getInstance().getCurrentUser();
                YStories.YStory yStory = (YStories.YStory) businessObject;
                if (currentUser != null) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.accumulate(UrlConstants.KEY_COMMENT, answerText);
                        jsonObject.accumulate(UrlConstants.KEY_CATEGORY, yStory.getCategory());
                        showProgressDialog(false, "Posting your comment...");
                        String postOnYstoryUrl = UrlConstants.COMMENT_POST_ON_YSTORY_URL;
                        postOnYstoryUrl = postOnYstoryUrl
                                .replace("{userid}", currentUser.getUserId())
                                .replace("{feedid}", yStory.getID());

                        FeedParams feedParams = new FeedParams(postOnYstoryUrl, String.class, null);
                        feedParams.setMethod(Request.Method.POST);
                        feedParams.setShouldCache(false);
                        FeedManager.getInstance().queueJobMultipart(feedParams, jsonObject.toString(), new Interfaces.IDataRetrievalListenerString() {
                            @Override
                            public void onDataRetrieved(String string) {
                                hideProgressDialog();
                                if (!TextUtils.isEmpty(string)) {
                                    try {
                                        string = string.replaceAll("\"", "");
                                        if (Integer.parseInt(string) > 0) {
                                            Toast.makeText(YouthActivity.this, getResources().getString(R.string.comment_posted_successful), Toast.LENGTH_SHORT).show();
                                        }
                                        onBackPressed();
                                    } catch (Exception e) {
                                        Toast.makeText(YouthActivity.this, getResources().getString(R.string.comment_posted_unsuccessful), Toast.LENGTH_SHORT).show();
                                        onBackPressed();
                                    }
                                } else {
                                    Toast.makeText(YouthActivity.this, getResources().getString(R.string.comment_posted_unsuccessful), Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                }
                            }
                        });
                    } catch (Exception e) {
                    }
                }
            }
        }

        public void voteTalentForumAnswer(Answers.Answer answer, int vote) {
            User currentUser = UserManager.getInstance().getCurrentUser();
            if(currentUser != null) {

                if(currentUser.getUserId().equalsIgnoreCase(answer.getAnswerByUserId())) {
                    Toast.makeText(YouthActivity.this, getResources().getString(R.string.cannot_vote_on_your_answer), Toast.LENGTH_SHORT).show();
                    return;
                }

                String jobApplyUrl = UrlConstants.VOTE_UP_TALENT_FORUM
                        .replace("{answerId}", String.valueOf(answer.getAnswerId()))
                        .replace("{userId}", currentUser.getUserId())
                        .replace("{vote}", String.valueOf(vote));

                showProgressDialog(true, "Voting...");
                FeedParams postFeedParams = new FeedParams(jobApplyUrl, VoteTalentForumAnswerResult.class, new Interfaces.IDataRetrievalListener() {
                    @Override
                    public void onDataRetrieved(BusinessObject businessObject) {
                        hideProgressDialog();
                        boolean isVoteApplied = false;
                        if (null != businessObject && businessObject.getVolleyError() == null && businessObject instanceof VoteTalentForumAnswerResult) {
                            if (!"0".equalsIgnoreCase(((VoteTalentForumAnswerResult) businessObject).getVoteTalentForumAnswerResult()))
                                isVoteApplied = true;
                        }

                        if (isVoteApplied)
                            Toast.makeText(YouthActivity.this, getResources().getString(R.string.vote_successful), Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(YouthActivity.this, getResources().getString(R.string.vote_unsuccessful), Toast.LENGTH_SHORT).show();
                    }
                });

                postFeedParams.setMethod(Request.Method.POST);
                postFeedParams.setShouldCache(false);
                FeedManager.getInstance().queueJob(postFeedParams);
            }
        }

        public void comment(BusinessObject businessObject) {
            if(businessObject instanceof Answers.Answer)
                changeFragment(R.id.CommentPage, null, null, businessObject);
        }

    }


*/
}
