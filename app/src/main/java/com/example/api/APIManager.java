package com.example.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import static com.android.volley.Request.Method;

/**
 * Created by Rajat on 6/6/2016.
 */
public class APIManager {
    ResponseInterface postResponseInterface;
    Context context;
    RequestQueue mQueue;
    String url;
    StringRequest jsonRequest;

    Response.Listener<String> defaultResponseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            postResponseInterface.parseResponse(response);
        }
    };
    Response.ErrorListener defaultErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(com.android.volley.error.VolleyError error) {
            postResponseInterface.ErrorResponse(error);
        }




    };
    public APIManager(Context context, ResponseInterface postResponseInterface) {
        this.context = context;
        this.postResponseInterface = postResponseInterface;
    }


        public void getPlanDetails(Map<String, String> values) {
        String params = "getPlanDetails";
        callAPIExternal(params, values);
        //callGetAPI(paraims);
    }
    public void callAllEmployee(Map<String, String> values) {
        String params = "getAllEmployees";
        callAPI(params, values);
        //callGetAPI(paraims);
    }

    public void getVisitorDetails(Map<String, String> values) {
        String params = "getVisitorDetail";
        callAPI(params, values);
        //callGetAPI(paraims);
    }
    protected void callPostAPI(String params, Response.Listener<String> responseListener, Response.ErrorListener errorListener, final Map<String, String> values) {
        mQueue = Volley.newRequestQueue(context);

    url="http://frontdesk.in/api/"+params;
   // url="http://admin.frodesk.com/api/"+params;

       Log.d("post api url","post api url"+ url);
        jsonRequest = new StringRequest(Method.POST, url,
                responseListener, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                // return super.getParams();
         Log.d("visitordetail","visitordetail "+values);

                return values;
            }
        };

        /******************Code to increase the timeout value of volley response**************/
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

//        /**********************************************************************************************/
        mQueue.add(jsonRequest);

    }

    protected void callPostAPIExternal(String params, Response.Listener<String> responseListener, Response.ErrorListener errorListener, final Map<String, String> values) {
        mQueue = Volley.newRequestQueue(context);
//url = "http://10.222.8.4/test.php/" + params;
        // url="http://10.222.8.220/frontdesk/api/"+params;

       // url="http://frontdesk.in/api/"+params;
        url="http://admin.frodesk.com/api/"+params;
        //url = "http://frontdesk.in/api/"+params;
        // http://localhost/test.php/
        //Log.d("visitordetail","visitordetail 1"+url );
        //Log.d("visitordetail","visitordetail 2"+params);


        Log.d("post api url","post api url"+ url);
        jsonRequest = new StringRequest(Method.POST, url,
                responseListener, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                // return super.getParams();
                Log.d("visitordetail","visitordetail "+values);

                return values;
            }
        };

        /******************Code to increase the timeout value of volley response**************/
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

//        /**********************************************************************************************/
        mQueue.add(jsonRequest);

    }



    protected void callAPI(String params, Map<String, String> values) {

        callPostAPI(params, defaultResponseListener, defaultErrorListener, values);
    }


    protected void callAPIExternal(String params, Map<String, String> values) {
        callPostAPIExternal(params, defaultResponseListener, defaultErrorListener, values);
    }




    public void getAllActiveVisitors(Map<String, String> values) {
        String params = "getVisitorByLocation";
        callAPI(params, values);
    }

    public void insertVisitorEntry(Map<String, String> values) {
        String params = "insertVisitorEntry";
        callAPI(params, values);

    }

    public void uploadImage(Map<String, String> values) {
        String params = "uploadImage";
        callAPI(params, values);
    }

    public void insertPaymentDetails(Map<String, String> values) {
        String params = "insertPaymentDetails";
        callAPI(params, values);
    }

   public void saveDeviceToken(Map<String, String> values) {
        String params = "deviceTokenSave";
        callAPIExternal(params, values);
    }

    public void employeeVerification(Map<String, String> values) {
        String params = "verifyEmployee";
        callAPI(params, values);
    }

    public void getEmpAssetWithVerification(Map<String, String> values) {
        String params = "getEmpAssetWithVerification";
        callAPI(params, values);
    }




    public void empAssetOut(Map<String, String> values) {
        String params = "insertNewMovement";

        callAPI(params, values);
    }

    public void getEmpAssets(Map<String, String> values) {
        String params = "getEmpAssets";
        callAPI(params, values);
    }

    public void markCompleteEmpAssetMovement(Map<String, String> values) {
        String params = "markCompleteEmpAssetMovement";

        callAPI(params, values);
    }

    public void empVerificationAttendance(Map<String, String> values) {
        String params = "empVerificationAttendance";
        callAPI(params, values);
    }

    public void getManagerDetails(Map<String, String> values) {
        String params = "getManagerDetails";
        callAPI(params, values);
    }

    public void manualAttendanceEntry(Map<String, String> values) {
        String params = "manualAttendanceEntry";
        callAPI(params, values);
    }

    public void manualAttendanceExit(Map<String, String> values) {
        String params = "manualAttendanceExit";
        callAPI(params, values);
    }

    public void insertCourierReceiptAtDesk(Map<String, String> values) {
        String params = "insertCourierReceiptAtDesk";
        callAPI(params, values);
    }

    public void getCourierByLocation(Map<String, String> values) {
        String params = "getCourierByLocation";
        callAPI(params, values);
    }

    public void markCourierDelivery(Map<String, String> values) {
        String params = "markCourierDelivery";
        callAPI(params, values);
    }

    public void markVisitorExitByLocation(Map<String, String> values) {
        String params = "markVisitorExitByLocation";

        callAPI(params, values);
    }

    public void verifyAppUser(Map<String, String> values) {
        String params = "loginuser";
        callAPIExternal(params, values);
    }


    public void verifyAppUserLoc(Map<String, String> values) {
        String params = "verifyAppUser";
        callAPI(params, values);
    }

    public void sighupUser(Map<String, String> values) {
        String params = "registeruser";
        callAPIExternal(params, values);
    }
    public void fetchPanelAccess(Map<String, String> values) {
        String params = "fetchPanelAccess";
        callAPI(params, values);
    }
    public void updateUserDetails(Map<String, String> values) {
        String params = "updateProfileDetails";
        callAPIExternal(params, values);
    }
    public void changePassword(Map<String, String> values) {
        String params = "updatePassword";
        callAPIExternal(params, values);
    }

    public void getFacilityAssetWithVerification(Map<String, String> values) {
        String params = "getFacilityAssetWithVerification";
        callAPI(params, values);
    }

    public void verifyFacilityLogin(Map<String, String> values) {
        String params = "verifyFacilityLogin";
        callAPI(params, values);
    }

   public void insertNewFacilityMovement(Map<String, String> values) {
        String params = "insertNewFacilityMovement";
        callAPI(params, values);
    }

    public void getFacilityAssets(Map<String, String> values) {
        String params = "getFacilityAssets";
        callAPI(params, values);
    }

    public void markCompleteFacilityAssetMovementAction(Map<String, String> values) {
        String params = "markCompleteFacilityAssetMovement";
        callAPI(params, values);
    }

    public void getAttendanceByLocation(Map<String, String> values) {
        String params = "getAttendanceByLocation";
        callAPI(params, values);
    }



    public void getLateWork(Map<String, String> values) {
        String params = "lateNightPermission";
        callAPI(params, values);
    }

    public void getTemporaryCards(Map<String, String> values) {
        String params = "getTemporaryCards";
        callAPI(params, values);
    }

    public void traineeEntry(Map<String, String> values) {
        String params = "traineeEntry";
        callAPI(params, values);
    }

    public void getTrainees(Map<String, String> values) {
        String params = "getTrainees";
        callAPI(params, values);
    }


    public void getMarkAttendanceTrainees(Map<String, String> values) {
        String params = "getTrainees";
        callAPI(params, values);
    }


    public void updateAttendanceInTrainees(Map<String, String> values) {
        String params = "temporaryTraineeEntry";
        callAPI(params, values);
    }


    public void updateAttendanceOutTrainees(Map<String, String> values) {
        String params = "markTemporaryTraineeExit";
        callAPI(params, values);
    }
    public void markTraineeExit(Map<String, String> values) {
        String params = "markTraineeExit";
        callAPI(params, values);

    }
    public void passwordChangeRequest(Map<String, String> values) {
        String params = "passwordChangeRequest";
        callAPIExternal(params, values);

    }

    public void checkManagerApprovalByLocation(Map<String, String> values) {
        String params = "checkMgrApprovalByLocation";
        callAPI(params, values);
    }

    public void manualAttendanceEntryBySecurity(Map<String, String> values) {
        String params = "manualAttendanceEntryBySecurity";
        callAPI(params, values);
    }

    public void pendingAttendanceByLocation(Map<String, String> values) {
        String params = "pendingAttendanceByLocation";
        callAPI(params, values);
    }

    public void totalAttendanceByLocation(Map<String, String> values) {
        String params = "totalAttendanceByLocation";
        callAPI(params, values);
    }

    public void totalVisitorByLocation(Map<String, String> values) {
        String params = "totalVisitorByLocation";
        callAPI(params, values);
    }

    public void getNightOwls(Map<String, String> values) {
        String params = "";
        callAPI(params, values);
    }

    public void temporaryTraineeEntry(Map<String, String> values) {
        String params = "temporaryTraineeEntry";
        callAPI(params, values);
    }

    public void markTemporaryTraineeExit(Map<String, String> values) {

        String params = "markTemporaryTraineeExit";
        callAPI(params, values);
    }

    public void verifyHRAppCredentials(Map<String, String> values) {
        String parmas = "verifyHRAppCredentials";


        callAPI(parmas, values);

    }

    public void getAllUserDetailByID(Map<String, String> values) {
        String parmas = "getUserDetailsbyid";
        callAPI(parmas, values);

    }
    public void fetchProfileDetails(Map<String, String> values) {
        String parmas = "fetchProfileDetails";
        callAPIExternal(parmas, values);

    }


}
