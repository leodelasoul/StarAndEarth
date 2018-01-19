package edu.berlin.htw.ds.cg;

import edu.berlin.htw.ds.cg.helper.GLDrawHelper;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import java.nio.FloatBuffer;


public class Main {

    private double len = 100;
    private double wid = 150;
    private int width = 800;
    private int height = 600;
    private int[] cameraPos;
    private boolean end = false;
    private boolean keyPressed = false;
    private boolean doWireframe = false;

    private int earthRot= 0;
    private int sunRot= 0;

    private FloatBuffer noAmbient;
    private FloatBuffer whiteDiffuse;
    private FloatBuffer position;

    public Main(){
        this.cameraPos = new int[]{0,0,500};
        this.noAmbient = GLDrawHelper.directFloatBuffer(new float[]{0.0f,0.0f,0.0f,1.0f});
        this.whiteDiffuse = GLDrawHelper.directFloatBuffer(new float[]{1.0f,1.0f,1.0f,1.0f});
        this.position = GLDrawHelper.directFloatBuffer(new float[]{0.0f,0.0f,0.0f,1.0f});
    }

    public static void main(String[] args) {

        Main trigger = new Main();
        trigger.run();

    }

    public void run(){
        setup();
        while (!end){
            update();
            render();
        }

    }

    public void setup() {

        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        GL11.glMatrixMode(GL11.GL_PROJECTION);

//        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glLoadIdentity();
        GLU.gluPerspective(45.f, width/(float)height, 0.1f, 3000.f);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glLight(GL11.GL_LIGHT0,GL11.GL_AMBIENT,noAmbient);
        GL11.glLight(GL11.GL_LIGHT0,GL11.GL_DIFFUSE,whiteDiffuse);
        GL11.glEnable(GL11.GL_LIGHT0);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK,GL11.GL_AMBIENT_AND_DIFFUSE);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);




    }


    public void update() {
        end = Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Display.isCloseRequested();

        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            if(!keyPressed) doWireframe = !doWireframe;
            keyPressed = true;
        }
        else {
            keyPressed = false;
        }

    }


    public void render() {

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0, 0, 0, 0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GLU.gluLookAt(cameraPos[0], cameraPos[1],cameraPos[2], 0,0,0, 0,1,0);
        GL11.glLight(GL11.GL_LIGHT0,GL11.GL_POSITION,position);


        if(doWireframe) {
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        }
        else {
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        }

        sunRot++;
        earthRot++;


        GL11.glPushMatrix();
        GL11.glTranslatef(0,0,0);
        GL11.glColor3f(1f,1f,0);
        GLDrawHelper.drawSphere(106,12,12);  //Sun
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glRotatef(sunRot,0.f, 1.f, 0.f);
        GL11.glTranslatef(200,0,0);
        GL11.glColor3f(0f,0f,1f);
        GL11.glRotatef(earthRot,0, 1, 0);
        GLDrawHelper.drawSphere(10,12,12); //Earth

        MainExtension.draw(); //Moon
        GL11.glPopMatrix();




        Display.update();
        Display.sync(60);
    }





}
