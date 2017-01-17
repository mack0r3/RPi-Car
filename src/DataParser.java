public class DataParser {

    public static MotorCoordinate ParseMotorCoordinate(String data) {
        
        if (data == null) {
            return null;
        }

        if (data.isEmpty()) {
            return null;
        }

        String strengthString = "0";
        String degreeString = "0";

        try {
            strengthString = data.substring(data.lastIndexOf("#") + 1);
            degreeString = data.substring(0, data.lastIndexOf("#"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        int strength = 0;
        int degree = 0;

        try {
            strength = Integer.parseInt(strengthString.toString().trim());
            degree = Integer.parseInt(degreeString.toString().trim());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new MotorCoordinate(strength, degree);
    }
}
