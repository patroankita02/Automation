package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild
{
    public AddPlace AddPlacePayload(String name, String language, String address)
    {
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("1231231234");
        p.setWebsite("https://rahulshetty.com");
        p.setName(name);

        List<String> mylist = new ArrayList<>();
        mylist.add("shoe park");
        mylist.add("shop");
        p.setTypes(mylist);

        Location l = new Location();
        l.setLat(-15.383494);
        l.setLng(10.427362);
        p.setLocation(l);
        return p;
    }

    public String deletePlacePayload(String placeId)
    {
        return "{\n" +
                "   \"place_id\":\""+placeId+"\"\n" +
                "}";
    }
}
