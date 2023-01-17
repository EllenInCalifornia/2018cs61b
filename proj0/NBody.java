public class NBody {
    //this is a static method, independent of instance variable
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        // number of planets 
        int num = in.readInt();
        //universe radius
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        Planet[] planets = new Planet[num];
        double radius = in.readDouble();
        int i = 0;
        while(i<num){
            
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();

            //the picture is in a sub-directory ***********
            String img = "images/" + in.readString();

            // no planet was created before this
            planets[i] = new Planet(xP,yP,xV,yV,m,img);
            i++;
        }
        
        
        return planets;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // detect if command line arguments are empty
        if(args.length != 0) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        //set the scale so that it matches the radius of the universe
        //长宽都设置成2倍的半径
        StdDraw.setXscale(-r, r);
        StdDraw.setYscale(-r, r);

        //set starfield to the background
        StdDraw.picture(0, 0, "images/starfield.jpg");

        // draw all planets
        for(Planet p : planets) {
            p.draw();
        }


        StdDraw.enableDoubleBuffering();

        // a time variable 
        double t = 0.0;

        while(t <= T ){
            double[] xForces = new double[planets.length];
            for(int i =0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);

            }
            double[] yForces = new double[planets.length]; 

            for(int i =0; i < planets.length; i++) {
                yForces[i] = planets[i].calcNetForceExertedByY(planets);

            }

            for(Planet p : planets) {
                p.update(dt, r, t);
            }
            
            //set starfield to the background
        StdDraw.picture(0, 0, "images/starfield.jpg");
        // draw all planets
        for(Planet p : planets) {
            p.draw();
        }
        StdDraw.show();
        //pause(int t) Pauses for t milliseconds.
        StdDraw.pause(10);
        t += dt;

        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}


    }


        
    }

}
