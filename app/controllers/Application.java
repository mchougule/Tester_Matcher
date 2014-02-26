package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.libs.Json;

//import org.codehaus.jackson.node.ArrayNode;
import play.mvc.BodyParser;

import models.*;
import views.html.*;
import java.util.*;

public class Application extends Controller {

    /*  We render the index page with device and tester information   */
    public static Result index() {
        return ok(index.render(
                Tester.find.all(),
                Device.find.all()
            ));
    }

    /*  getUsers method, fired when user clicks on tester select box    */
    public static Result getUsers(String country) {
        /*  Find tester by coutnry  */
        List<Tester> tList = Tester.findTester(country);

        /* Return the list and render it to testers template */
        return ok( 
            testers.render( tList
                )
            );
    }

    /*  getBugs method, fired when user clicks to find bugs */
   // @BodyParser.Of(Json.class)
    public static Result getBugs(String id, String devId) {

        /*  Converted into array because we can choose multiples    */
        String[] idArray = id.split(",");
        String[] devIdArray = devId.split(",");
        String t = "";
        String td = "";
        /*  List to store the bugs */
        List<Bug> bList = new ArrayList<Bug>();

        /*  HashMap with Integer and above List of Bug obejcts. We return this */
        Map<Integer, List<Bug>> newMap = new LinkedHashMap<Integer, List<Bug>>();
        int counter = 1;

        /*
            TODO: OPTIMIZE THIS
        */
        /*  Loop through the testerId, we called here as just id */
        for( int i = 0; i < idArray.length; i++) {
            t = idArray[i];

            /*  Loop through the devices selected */
            for ( int j = 0; j < devIdArray.length; j++) {
                td = devIdArray[j];
                counter++;

                /*  Parse the String as Long and call our model */
                bList = Bug.findBugs(Long.parseLong(t), td);

                /*   Put the returned result into the map   */
                newMap.put(counter, bList);
            }
        }

   /*     ObjectNode result = Json.newObject();
        result.put("Status", "OK");
        result.put("message", newMap);
*/
        /*    Return OK status and render the map to bugs template    */
        return ok( Json.toJson( newMap ) );
//            bugs.render(
  //              newMap
    //            )
      //      );
    }

}
