package edu.berlin.htw.ds.cg;

import edu.berlin.htw.ds.cg.helper.GLDrawHelper;
import org.lwjgl.opengl.GL11;

public class MainExtension extends Main{



    public MainExtension(){

    }

    public static void draw(){

        GL11.glPushMatrix();
        GL11.glRotatef(360,20, 0, 0.f);
        GL11.glTranslatef(50,0,0);
        GL11.glColor3f(1f,1f,1f);
        GLDrawHelper.drawSphere(5,12,12);  //Moon
        GL11.glPopMatrix();


    }

}
