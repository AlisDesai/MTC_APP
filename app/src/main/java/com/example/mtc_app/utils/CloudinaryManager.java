package com.example.mtc_app.utils;

import com.cloudinary.Cloudinary;
import java.util.HashMap;
import java.util.Map;

public class CloudinaryManager {
    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", "dpqonar5z");
            config.put("api_key", "781882939268934");
            config.put("api_secret", "RIeoigRZw8-e9SRiCi6ACHEWULk");
            cloudinary = new Cloudinary(config);
        }
        return cloudinary;
    }
}
