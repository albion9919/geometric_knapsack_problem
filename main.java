import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.io.*;
//import java.util.List;
//import java.util.Iterator;
//import java.util.*;

interface path
{String path = "C:\\Users\\Олег\\Desktop\\";}

public class Packing implements path{
    public static void main(String args[]){


       // Retext r = new Retext();
        //r.Refile(path+"rr5.txt",path+"r5.txt");
        //LoadRectangles L = new LoadRectangles();
        //GenAlgoritm Gen = new GenAlgoritm();

        Field F1 = new Field(40,30) ;
        Field F2 = new Field(50,20) ;
        Field F3 = new Field(20,10) ;
        Field F4= new Field(20,10) ;
        Field F5 = new Field(1500,500);
       /* SettingsForHTML Sett = new SettingsForHTML(10,12,true);
        SettingsForHTML Sett2 = new SettingsForHTML(30,12,true);
        ArrayList<Rectangle>mas=L.LoadRectDesktop("r1.txt");
        ArrayList<Rectangle>mas2=L.LoadRectDesktop("r2.txt");
        ArrayList<Rectangle>mas3=L.LoadRectDesktop("r3.txt");
        ArrayList<Rectangle>mas4=L.LoadRectDesktop("r4.txt");
        Set s1 = Gen.MainAlgoritm(mas,F1);
        Set s2 = Gen.MainAlgoritm(mas2,F2);
        Set s3 = Gen.MainAlgoritm(mas3,F3);
        Set s4 = Gen.MainAlgoritm(mas4,F4);
        Gen.saveToFile(s1,mas,F1, Sett,path,"h1");
        Gen.saveToFile(s2,mas2,F2, Sett,path,"h2");
        Gen.saveToFile(s3,mas3,F3, Sett2,path,"h3");
        Gen.saveToFile(s4,mas4,F4, Sett2,path,"h4");
        GenAlgoritm Gen1=new GenAlgoritm();
        GenAlgoritm Gen2=new GenAlgoritm();
        GenAlgoritm Gen3=new GenAlgoritm();
        GenAlgoritm Gen4=new GenAlgoritm(); */
        SettingsForHTML Sett = new SettingsForHTML(10,12,true);
        SettingsForHTML Sett2 = new SettingsForHTML(10,12,true);
        SettingsForHTML Sett3 = new SettingsForHTML(30,12,true);
        SettingsForHTML Sett4 = new SettingsForHTML(30,12,true);
        SettingsForHTML Sett5 = new SettingsForHTML(0.3,12,true);
        Thr T1 = new Thr("r1.txt",F1,Sett,"h1");
        Thr T2 = new Thr("r2.txt",F2,Sett2,"h2");
        Thr T3 = new Thr("r3.txt",F3,Sett3,"h3");
        Thr T4= new Thr("r4.txt",F4,Sett4,"h4");
        Thr T5= new Thr("r5.txt",F5,Sett5,"h5");
        //T1.run();
        //T2.run();
        //T3.run();
        //T4.run();
        /*ArrayList<Rectangle>mas5=L.LoadRectDesktop("r5.txt");
        Gen.Setting.LoadMaxStandartSettings();
        Set s5 = Gen.MainAlgoritm(mas5,F5);
        Gen.saveToFile(s5,mas5,F5, Sett3,path,"h5");
        System.out.println(s5.GetPercentages());
        System.out.println(s5.GetCount());
        System.out.println("Best.get(0).CountOfSolution = " + s5.CountOfSolution);
        System.out.println("Best.get(0).numberofiter = " + s5.numberofiter); */

    }
}
//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class Thr implements Runnable,path
{Field F;

    ArrayList<Rectangle>Rmas;
    SettingsForHTML Sett;
    String Name;
    GenAlgoritm Gen;

    Thr(String Load,Field FX, SettingsForHTML Settx,String N)//,GenAlgoritm Genx
    {F=FX;
        LoadRectangles L = new LoadRectangles();
        Sett=Settx;
        Rmas=L.LoadRectDesktop(Load);
        Name=N;
        Gen=new GenAlgoritm();
        Gen.Setting.LoadMaxStandartSettings();
        new Thread(this, "").start();}


    public void run()
    { Set s = Gen.MainAlgoritm(Rmas,F);
        System.out.println(s.GetPercentages());
        System.out.println(s.GetCount());
        Gen.saveToFile(s,Rmas,F, Sett,path,Name);}


}
//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class InvalidSettingsOfAlgoritm extends Exception
{public void GetCause(){System.out.println("You set Invalid Settings Of Algoritm.Please,reset.");}}

class InvalidFillingOfFile extends Exception
{public void GetCause(){System.out.println("Fill of RectMas was filled incorrect.Please,refill.");}}

class  InvalidFillingOfSettingsFile extends Exception
{public void GetCause(){System.out.println("Fill of settings was filled incorrect.Please,refill.");}}

class SettingsForHTMLException extends Exception
{public void GetCause(){System.out.println("You set Invalid Settings Of HtmlSaveAlgoritm.Please,reset.");}}
//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class Set implements Cloneable
{public int[] set;
    public int[] x;
    public int[]y;
    public int []y2;
    public int []x2;
    private double Percentages;
    private byte ByWyOrLe;// 1-W, 2-L
    private int CountOfRect;
    public int numberofiter;
    public int CountOfSolution;

    public  Set(int[] z)
    {set=z;
        x=new int[set.length];
        x2=new int[set.length];
        y=new int[set.length];
        y2=new int[set.length];}; //?  getlength for N

    public int[] GetSet(){return set;};//?
    public void setSet(int[]x){set=x;};//?
    public void SetCount (int x,double y){CountOfRect=x;Percentages=y;}
    public int GetCount (){return CountOfRect;}
    public double GetPercentages(){return Percentages;}

    protected Object clone () throws CloneNotSupportedException
    {
        Set x = (Set)super.clone();
        x.set=Arrays.copyOf(this.set,this.set.length);
        x.x=Arrays.copyOf(this.x,this.x.length);
        x.x2=Arrays.copyOf(this.x2,this.x2.length);
        x.y=Arrays.copyOf(this.y,this.y.length);
        x.y2=Arrays.copyOf(this.y2,this.y2.length);
        x.numberofiter=numberofiter;;
        x.CountOfSolution=CountOfSolution;
        return x;
    }

}
//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class CompSet implements Comparator<Set> {
    @Override
    public int compare(Set o1, Set o2) {
        if  (o1.GetPercentages()>o2.GetPercentages())   return (-1);  //-1
        if  (o1.GetPercentages()<o2.GetPercentages())    return (1); //1
        return (0);
    }}


class CompInterval implements Comparator<Interval> {
    @Override
    public int compare(Interval o1, Interval o2) {
        if  (o1.getx1()>o2.getx1())   return (1);
        if  (o1.getx1()<o2.getx1())    return (-1);
        return (0);
    }}
//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class Rectangle //Rec
{private int length,width,Sxy;
    public  int getwidth(){return width;};
    public int getlength(){return length;};
    public int getSxy(){return Sxy;};
    public void Setwidth(int w){if(Checkwidth(w)){ width=w;SetSxy();}};
    public void Setlength(int l){if(Checklength(l)){ length=l;SetSxy();}};
    public void SetSxy(){Sxy=length*width;};
    public boolean Checkwidth(int w){return (w>0);};
    public boolean Checklength(int l){return (l>0);};
    public Rectangle (int x,int y) {length=x;width=y;Sxy=width*length;};
    public String toString(){return "length = "+length+" width = "+width;}
}
//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class Interval  //ColStr
{ private int x1,x2,y;
    public Interval(){};
    public Interval (int a,int b,int h){x1=a; x2=b;y=h;};
    public void SetInterval(int a,int b,int h){x1=a; x2=b;y=h;};
    public int getx1(){return x1;};
    public int getx2(){return x2;};
    public int gety(){return y;};
    public int getlength () {return (getx2()-getx1());};
    public int getwidth () {return (y);};
    public String toString(){return "x1="+x1+"x2="+x2;} //new String ();
}
//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class results
{results(int x1,int x2){l=x1;maxw=x2;}
    results (){};
    int l;
    int maxw;}
//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class Field
{private int length, width;
    private int Sxy;
    private boolean p,p2;


    public Field(int x, int y)
    { length = x;
        width = y;
        Sxy=x*y;
        p=false;
        p2=false; }
    public Field(int x, int y,boolean px)
    { length = x;
        width = y;
        Sxy=x*y;
        p=px;
        p2=false; }
    public Field(int x, int y,boolean px,boolean px2)
    { length = x;
        width = y;
        Sxy=x*y;
        p=px;
        p2=px2; }

    public void SetProperties(boolean px){p=px;}
    public void SetProperties(boolean px,boolean px2){p=px;p2=px2;}
    public void SetLength(int l){length=l;SetSxy();}
    public void SetWidth(int w){width=w;SetSxy();}
    public void SetSize(int l,int w){length=l;width=w;SetSxy();}
    private void SetSxy(){Sxy=length*width;}
    public void GetProperties(){System.out.println("p = "+p+" p2 = "+p2);}
    public void GetSize(){System.out.println("length = "+length+" width = "+width);}
    public int GetWidth(){return width;}
    public int GetLength(){return length;}
    private void GetSxy(){System.out.println("Sxy = "+Sxy);}


    public void Place(Set S, Rectangle[] Rmas)
    {PlaceRects(S,Rmas);}
    public void Place(Set S, ArrayList<Rectangle> Rmas)
    {Rectangle[] Rarray = (Rectangle[]) Rmas.toArray(new Rectangle[Rmas.size()]);
        PlaceRects(S,Rarray);}



    private void PlaceRects(Set S,Rectangle[]Rmas)
    { ArrayList<Interval> ListOfIntervals = new ArrayList<>();
        ArrayList<Interval> ListOfIntervals2 = new ArrayList<>();
        int FullSxy;
        int FullLength;
        int level=1;
        FullSxy = 0;
        FullLength=0;
        int i=0;
        int left=0;
        int m=0;

        FullSet:
        while (Rmas[S.set[i]].getSxy()+FullSxy<=Sxy)
        { results res ;
            FullLen:
            while(Rmas[S.set[i]].getlength()+FullLength<=length)
            {
                if (level==1){res=new results(); res.l=left+Rmas[S.set[i]].getlength(); res.maxw=0;}
                else {res=MaxWidth(ListOfIntervals2,left,left+Rmas[S.set[i]].getlength());}
                if(p2) {System.out.println("res.maxw= "+res.maxw+" res.l= "+res.l); }
                m=0;

                if (res.maxw+Rmas[S.set[i]].getwidth()<=width)
                {ListOfIntervals.add(new Interval(left,left+Rmas[S.set[i]].getlength(),Rmas[S.set[i]].getwidth()+res.maxw));   m=1;
                    S.x[i]=left;
                    S.x2[i]=Rmas[S.set[i]].getlength();
                    S.y[i]=res.maxw;
                    S.y2[i]=Rmas[S.set[i]].getwidth();
                    if(p2){ System.out.println("i = "+i+" Rmas["+S.set[i]+"] length = "+Rmas[S.set[i]].getlength()+" width = "+Rmas[S.set[i]].getwidth());
                        System.out.println("level = "+level);}
                }

                else
                {left=res.l;
                    FullLength=res.l;}



                if (i<S.GetSet().length) {  //i+1
                    if(m==1) { left = left + Rmas[S.set[i]].getlength();
                        FullLength += Rmas[S.set[i]].getlength();
                        FullSxy += Rmas[S.set[i]].getSxy();
                        if(p)System.out.println("FullSxy = "+ FullSxy);
                        i++;}
                }
                else  {break FullSet;}
                if(res.l==-1) break FullLen;

                if(i==S.GetSet().length)
                    break FullSet;

                if ((i!=S.GetSet().length)&&((Rmas[S.set[i]].getSxy()+FullSxy>Sxy)))
                    break FullSet;
            }



            if (ListOfIntervals.size()==0) break FullSet;

            if((level==1)&&(ListOfIntervals.get(ListOfIntervals.size()-1).getx2()!=length))
            {ListOfIntervals.add(new Interval(ListOfIntervals.get(ListOfIntervals.size()-1).getx2(),length,0));
                if(p2) System.out.println("Crit");}

            FullLength=0;
            left=0;
            level++;
            if (p2) for (int j = 0; j < ListOfIntervals.size(); j++)
            {System.out.println(j + 1 + ") x1=" + ListOfIntervals.get(j).getx1() + " x2=" +ListOfIntervals.get(j).getx2() + " y=" + ListOfIntervals.get(j).gety());}


            if (ListOfIntervals2.size()!=0) {UpdateIntervals(ListOfIntervals,ListOfIntervals2);}
            Collections.sort(ListOfIntervals,new CompInterval());
            ListOfIntervals2.clear();
            ListOfIntervals2.addAll(ListOfIntervals);
            ListOfIntervals = new ArrayList<>();


            if (p2) if (level>2) for (int j = 0; j < ListOfIntervals2.size(); j++)
            {System.out.println(j + 1 + ") x1=" + ListOfIntervals2.get(j).getx1() + " x2=" + ListOfIntervals2.get(j).getx2() + " y=" + ListOfIntervals2.get(j).gety());}

        }


        double a=FullSxy;
        double b=Sxy;
        //if (i<S.GetSet().length)S.SetCount(i+1, a / b);
        //if (i==S.GetSet().length)
        S.SetCount(i, a / b);
        if(p)System.out.println("Perc = "+a / b);
    }






    private results MaxWidth( ArrayList<Interval>  I, int x1, int x2) {  //v
        int maxw = 0, i = 0,l=0;
        results res = new results();

        while ((i < I.size()) && (x1 >= I.get(i).getx2()))
        { i++; }

        if (i != I.size()) {maxw = I.get(i).getwidth();  l = I.get(i).getx2();}

        while ((i + 1 < I.size()) && (x2 > I.get(i).getx2())) {
            i++;

            if (maxw < I.get(i).getwidth())
            { maxw = I.get(i).getwidth();
                l = I.get(i).getx2(); }

            if ((i == 1) && (I.get(0).getwidth() > I.get(1).getwidth()))
            { maxw = I.get(0).getwidth();
                l = I.get(0).getx2();

                if (I.get(0).getx2() == x1)
                { maxw = I.get(1).getwidth();
                    l = I.get(1).getx2(); } }
        }


        if (i == I.size())
        { res .maxw = width;
            res.l=-1;
            return res; }

        if ((x2 <= I.get(i).getx2()) && (i == 0))
        { maxw = I.get(i).gety(); };

        res.maxw=maxw;
        res.l=l;
        return res;
    }





    private void UpdateIntervals( ArrayList<Interval>  I,  ArrayList<Interval>  I2)
    {ArrayList<Interval> ListOfIntervals = new ArrayList<>();
        ArrayList<Interval> UpdateIntervals = new ArrayList<>();

        int j = 0, k = 0;

        if ((I.get(0).getx1() !=0)){
            Interval NewI;
            NewI = new Interval(0, I.get(0).getx1(),0);
            ListOfIntervals.add(NewI); }

        for(int i = 0; i<I.size();i++)
        { if ((i<I.size()-1)&&(I.get(i).getx2() != I.get(i+1).getx1())) {
            ListOfIntervals.add( new Interval(I.get(i).getx2(), I.get(i+1).getx1(),0));}
        else
        {if (I.get(i).getx2() !=length && i+1==I.size()) {ListOfIntervals.add( new Interval(I.get(i).getx2(), length,0));}} }


        for(k =0; k<ListOfIntervals.size(); )
        { for (j = 0; j<I2.size()&&ListOfIntervals.get(k).getx1() >= I2.get(j).getx2(); ) { j++; }

            if (j < I2.size() ) {
                Interval NewI = new Interval(ListOfIntervals.get(k).getx1(), I2.get(j).getx2(),I2.get(j).gety());
                UpdateIntervals.add(NewI);

                if (ListOfIntervals.get(k).getx2() > I2.get(j).getx2()) {
                    ListOfIntervals.get(k).SetInterval(I2.get(j).getx2(), ListOfIntervals.get(k).getx2(),0);
                } else {k++;} } }


        I.addAll(UpdateIntervals);
    }


}
//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class RandomRectSet
{ RandomRectSet(){};
    int[] RandomSet(int MaxNumber)
    {int []ar=null;
        if (MaxNumber>0)
        {ar=new int[MaxNumber];
            Random rnd = new Random();

            for (int i = 0; i < ar.length ; i++)
            {ar[i]=i;}

            for (int i = ar.length - 1; i > 0; i--)
            { int index = rnd.nextInt(i + 1);
                int a = ar[index];
                ar[index] = ar[i];
                ar[i] = a; } }
        return ar;
    };
}
//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------

class GenAlgoritm implements path
{ private ArrayList<Set> s;
    private Random rnd;
    Settings Setting;
    Field F;
    Rectangle[] Rmas;
    String PathSettings;
    private ArrayList<Set> Best;
    SaveToHTML Save;
    private boolean Ot;
    private String StandartName;
    int CountOfSolution;


 /*   public GenAlgoritm(String path)
    { rnd=new Random();
    s = new ArrayList<>();
        CreateSetiings(path);}*/

    public GenAlgoritm(String PathSettingsx,boolean o)
    { CreateSetiings(PathSettingsx); Ot=o; Save=new SaveToHTML();String StandartName="h.html";}
    public GenAlgoritm(String PathSettingsx)
    { CreateSetiings(PathSettingsx); Save=new SaveToHTML();String StandartName="h.html";}
    public GenAlgoritm()
    { CreateSetiings(); PathSettings="C:\\1\\StandartSettings.txt"; Save=new SaveToHTML();StandartName="h.html";}
    public GenAlgoritm(boolean o)
    { CreateSetiings(); Ot=o; PathSettings="C:\\1\\StandartSettings.txt"; Save=new SaveToHTML();StandartName="h.html";}


    public void  SetPathSettings(String PathSettingsx)
    { CreateSetiings(PathSettingsx);
        PathSettings=PathSettingsx;}
    public void  SetOt(boolean o)
    {Ot=o;}

    public void  GetPathSettings()
    {System.out.println("PathSettings = "+PathSettings); }
    public void  GetOt()
    {System.out.println("Ot = "+Ot);}

    private void CreateSetiings()
    { Setting = new Settings();
        Setting.LoadStandartSettings();}

    private void CreateSetiings(String path)
    { Setting = new Settings();
        Setting.LoadSettings(path);}



    private void CreateSet(int MaxNumber)
    {RandomRectSet Ran =new RandomRectSet();
        int[]x;
        x=Ran.RandomSet(MaxNumber);
        Set NewSet =  new Set(x);
        F.Place(NewSet,Rmas);
        s.add(NewSet);
        CountOfSolution++;}


    private void CreateSets(int MaxNumber) //m-Rmas.length,
    {RandomRectSet Ran =new RandomRectSet();
        int[]x;
        //Random rnd = new Random();
        for (int i=0;i<Setting.Get_Capacity_Population();i++)
        { x=Ran.RandomSet(MaxNumber);
            Set NewSet =  new Set(x);
            F.Place(NewSet,Rmas);
            s.add(NewSet);}
        CountOfSolution+=Setting.Get_Capacity_Population();};



    private void Cross (int num) //type set
    {//  I2 = Arrays.copyOf(I, I.length);
        double p;
        int i=0;
        int n1;
        int m1;
        int m2;
        int bu;

        while (i<num)
        {p=rnd.nextDouble();
            if (p<=Setting.Get_p_cros())
            { n1=rnd.nextInt(s.size()-1);
                int [] time11=s.get(n1).GetSet();
                m1=rnd.nextInt(s.get(0).GetSet().length/2);
                m2=m1+rnd.nextInt(s.get(0).GetSet().length/2-1);
                bu=time11[m1];
                time11[m1]= time11[m2];
                time11[m2]=bu;
                F.Place( s.get(n1),Rmas);
                CountOfSolution++;
            }
            i++;
        }}



    private void Mutation(int num) {
        int m1, n1, n2, i = 0;
        double p;
        while (i < num) {
            p = rnd.nextDouble();
            if (p <= Setting.Get_p_mut()) {
                n1=rnd.nextInt(s.size()-1);
                m1=rnd.nextInt(s.get(n1).GetSet().length-1);
                int [] t=new int[s.get(n1).set.length];

                for (int j=0;j+m1<s.get(n1).GetSet().length;j++)
                {   t[j]=s.get(n1).GetSet()[j+m1]; }

                for (int j=0;t.length-m1+j<t.length;j++)
                {   t[t.length-m1+j]=s.get(n1).GetSet()[j]; }

                s.get(n1).setSet(t);
                F.Place( s.get(n1),Rmas);
                CountOfSolution++;}

            i++; }
    }



    private void GetSum(Rectangle[] Rmasx)
    {int sum=0;
        for (int i=0;i<Rmasx.length;i++)
            sum+=Rmas[i].getlength()*Rmasx[i].getwidth();
        if (Ot) System.out.println("Sum = "+sum);};



    Set MainAlgoritm (ArrayList<Rectangle>RmasTemp,Field FX)
    {rnd=new Random();
        CountOfSolution=0;
        Best  = new ArrayList<>();
        s  = new ArrayList<>();
        Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        F=FX;
        CreateSets(Rmas.length);
        Set B=null;

        MainAlgoritmx();
        try { B=(Set) Best.get(0).clone();} catch (Exception e){e.printStackTrace();}

        return B;}


    Set MainAlgoritm (Rectangle[]Rmasx,Field FX)
    {rnd=new Random();
        CountOfSolution=0;
        Best  = new ArrayList<>();
        s  = new ArrayList<>();
        Rmas=Rmasx;
        F=FX;
        CreateSets(Rmas.length);
        Set B=null;

        MainAlgoritmx();
        try { B=(Set) Best.get(0).clone();} catch (Exception e){e.printStackTrace();}

        return B;}





    private void MainAlgoritmx ()
    {int i=0;
        CompSet C_1=new CompSet();
        System.out.println("");
        if (Ot) GetSum(Rmas);
        if (Ot) System.out.println("Start Alg");
        System.out.println("");
        ee:
        while ((i<Setting.Get_Capacity_Generations())&&(s.get(0).GetPercentages()!=1))
        {Collections.sort(s,C_1);

            if (s.get(0).GetPercentages()==1)
            { try{Best.add((Set) s.get(0).clone());
                Best.get(Best.size()-1).numberofiter=i+1; }
                catch (Exception e) {e.printStackTrace();}
                break ee;}
            else {
                Cross(Setting.Get_count_of_cross());
                Mutation(Setting.Get_count_of_mut());
                try
                {int ii=0;
                    while(ii<Setting.Get_Best_Size() && ii<s.size())
                    { Best.add((Set)s.get(ii).clone());   Best.get(Best.size()-1).numberofiter=i+1;
                        ii++;}}
                catch (Exception e) {e.printStackTrace();}

                for (int j=Setting.Get_Good_Size();j<s.size()-Setting.Get_Bad_Size()-1;j++)
                {s.remove(j);}
                i++; }

                if(Setting.Get_Limit()) {while (s.size()!=Setting.Get_Capacity_Population()) {
                if (s.size() > Setting.Get_Capacity_Population())  s.remove(s.size()/2);
                if (s.size() < Setting.Get_Capacity_Population()) CreateSet(Rmas.length);}}
                CountOfSolution =  CountOfSolution  +Setting.Get_Capacity_Population()-Setting.Get_Good_Size()-Setting.Get_Bad_Size();
        }

        Collections.sort(s,C_1);
        if(s.get(0).GetPercentages()!=1) try { Best.add((Set)s.get(0).clone());} catch(Exception e){e.printStackTrace();}

        Collections.sort(Best,C_1);
        if (Ot) System.out.format("Percanteges = %f",Best.get(0).GetPercentages());
        if (Ot) System.out.println("");
        //if(Best.size()==0)   try{Best.add((Set) s.get(0).clone()); Best.get(Best.size()-1).numberofiter=1; } catch (Exception e) {e.printStackTrace();}
        Best.get(0).CountOfSolution=CountOfSolution;


        System.out.println("Best.get(0).CountOfSolution = " +Best.get(0).CountOfSolution);
        System.out.println("Best.get(0).numberofiter = " + Best.get(0).numberofiter);

        if (Ot)
        {int FullSxy=0;
            for (int iii=0;iii<Best.get(0).GetCount()-1;iii++)
            {System.out.println("i="+iii+" Rect["+iii+"]="+Best.get(0).set[iii]);
                FullSxy+=Rmas[Best.get(0).set[iii]].getSxy();
                System.out.println("FullSxy = "+ FullSxy);}
        }

    };



    public void saveToFile(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett,String PathSave,String Name)
    {try {Save.saveToFilex( SS, Rmas,F, Sett,PathSave+Name+".html");} catch(NullPointerException e){e.getMessage();}}
    public void saveToFileDesktop(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett,String Name)
    {try {Save.saveToFilex( SS, Rmas,F, Sett,"C:\\Users\\Олег\\Desktop\\"+Name+".html");} catch(NullPointerException e){e.getMessage();}}
    public void saveToFile_1(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett,String Name)
    {try {Save.saveToFilex( SS, Rmas,F, Sett,"C:\\1\\Олег\\Desktop\\"+Name+".html");} catch(NullPointerException e){e.getMessage();}}
    public void saveToFile(Set SS,ArrayList<Rectangle>RmasTemp,Field F,SettingsForHTML Sett,String PathSave,String Name)
    {try {Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        Save.saveToFilex( SS, Rmas,F, Sett,PathSave+Name+".html");} catch(NullPointerException e){e.getMessage();}}
    public void saveToFileDesktop(Set SS,ArrayList<Rectangle>RmasTemp,Field F,SettingsForHTML Sett,String Name)
    {try {Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        Save.saveToFilex( SS, Rmas,F, Sett,"C:\\Users\\Олег\\Desktop\\"+Name+".html");} catch(NullPointerException e){e.getMessage();}}
    public void saveToFile_1(Set SS,ArrayList<Rectangle>RmasTemp,Field F,SettingsForHTML Sett,String Name)
    {try {Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        Save.saveToFilex( SS, Rmas,F, Sett,"C:\\1\\Олег\\Desktop\\"+Name+".html");} catch(NullPointerException e){e.getMessage();}}
    public void saveToFile(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett,String PathSave)
    {try {Save.saveToFilex( SS, Rmas,F, Sett,PathSave+StandartName);} catch(NullPointerException e){e.getMessage();}}
    public void saveToFileDesktop(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett)
    {try {Save.saveToFilex( SS, Rmas,F, Sett,"C:\\Users\\Олег\\Desktop\\"+StandartName);} catch(NullPointerException e){e.getMessage();}}
    public void saveToFile_1(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett)
    {try {Save.saveToFilex( SS, Rmas,F, Sett,"C:\\1\\Олег\\Desktop\\"+StandartName);} catch(NullPointerException e){e.getMessage();}}
    public void saveToFile(Set SS,ArrayList<Rectangle>RmasTemp,Field F,SettingsForHTML Sett,String PathSave)
    {try {Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        Save.saveToFilex( SS, Rmas,F, Sett,PathSave+StandartName);} catch(NullPointerException e){e.getMessage();}}
    public void saveToFileDesktop(Set SS,ArrayList<Rectangle>RmasTemp,Field F,SettingsForHTML Sett)
    {try {Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        Save.saveToFilex( SS, Rmas,F, Sett,"C:\\Users\\Олег\\Desktop\\"+StandartName);} catch(NullPointerException e){e.getMessage();}}
    public void saveToFile_1(Set SS,ArrayList<Rectangle>RmasTemp,Field F,SettingsForHTML Sett)
    {try {Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        Save.saveToFilex( SS, Rmas,F, Sett,"C:\\1\\Олег\\Desktop\\"+StandartName);} catch(NullPointerException e){e.getMessage();}}



    // Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);

}

//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class Settings
{private double p_mut,p_cros;
    private int count_of_cross;
    private int count_of_mut;
    private int Good_Size,Bad_Size;//Вычилсять на основе размера
    private int Capacity_Generations;
    private int Capacity_Population; //10
    private int Best_Size;
    private boolean Limit;

    public void Set_p_cros(double p){if(!Check_p_cros(p)) p_cros=p; }
    public void Set_p_mut(double p){if(!Check_p_mut(p)) p_mut=p;}
    public void Set_count_of_cross(int n){if(!Check_count_of_cross(count_of_cross))count_of_cross=n;}
    public void Set_count_of_mut(int n){if(!Check_count_of_mut(count_of_mut))count_of_mut=n;}
    public void Set_Good_Size(int n){if(!Check_Good_Size(n))Good_Size=n;}
    public void Set_Bad_Size(int n){if(!Check_Bad_Size(n))Bad_Size=n;}
    public void Set_Capacity_Generations(int n){if(!Check_Capacity_Generations(n))Capacity_Generations=n;}
    public void Set_Capacity_Population(int n){if(!Check_Capacity_Population(n))Capacity_Population=n;}
    public void Set_Best_Size(int n){if(!Check_Best_Size(n))Best_Size=n;}
    public void Set_Limit(boolean b){Limit=b;}

    private boolean Check_p_cros(double p){return (p>1||p<0);}
    private  boolean Check_p_mut(double p){return (p>1||p<0);}
    private  boolean Check_count_of_cross(int n){return(n<0);}
    private  boolean Check_count_of_mut(int n){return(n<0);}
    private  boolean Check_Good_Size(int n){return(n>0.8*Capacity_Population);}
    private  boolean Check_Bad_Size(int n){return(n>0.4*Capacity_Population);}
    private  boolean Check_Capacity_Generations(int n){return(n<0||n>300);}
    private  boolean Check_Capacity_Population(int n){return(n<0||n>1000);}
    private  boolean Check_Best_Size(int n){return(n<1||n>Capacity_Population*0.1);}

    public double Get_p_cros(){return p_cros;}
    public double Get_p_mut(){return p_mut;}
    public int Get_count_of_cross(){return count_of_cross;}
    public int Get_count_of_mut(){return count_of_mut;}
    public int Get_Good_Size(){return Good_Size;}
    public int Get_Bad_Size(){return Bad_Size;}
    public int Get_Capacity_Generations(){return Capacity_Generations;}
    public int Get_Capacity_Population(){return Capacity_Population;}
    public int Get_Best_Size(){return Best_Size; }
    public boolean Get_Limit(){return Limit; }


    public void LoadSettings(String s) {
        boolean InvalidSettings=false;
        try {
            try {
                try {
                    FileReader InputStream = new FileReader(s);
                    Scanner scan = new Scanner(InputStream);//.useDelimiter("\\;");
                    ArrayList<String> StringList = new ArrayList<>();
                    while (scan.hasNextLine()) {
                        String s2[] = scan.nextLine().split("=");   //  = \|| =\||=
                        if (s2.length < 2) throw new InvalidFillingOfSettingsFile();//уточнить длину после split
                        StringList.add(s2[1].trim());}
                    InputStream.close();


                    try
                    {int i=0;
                        p_mut=  Double.parseDouble(StringList.get(i)); i++;
                        p_cros= Double.parseDouble(StringList.get(i));i++;
                        count_of_cross=Integer.parseInt(StringList.get(i));i++;
                        count_of_mut=Integer.parseInt(StringList.get(i));i++;
                        Capacity_Generations=Integer.parseInt(StringList.get(i));i++;
                        Capacity_Population=Integer.parseInt(StringList.get(i));i++;
                        Good_Size=Integer.parseInt(StringList.get(i));i++;
                        Bad_Size=Integer.parseInt(StringList.get(i));i++;
                        Best_Size=Integer.parseInt(StringList.get(i));i++;
                        Limit=Boolean.parseBoolean(StringList.get(i));
                    }
                    catch(Exception e){e.printStackTrace(); System.out.println("not enough parameters");}


                } catch (InvalidFillingOfSettingsFile e) { e.GetCause(); }
            } catch (FileNotFoundException e) { e.printStackTrace(); }
        }catch(IOException e) {e.printStackTrace();}

        try {if (CheckSettings()==InvalidSettings) throw new InvalidSettingsOfAlgoritm();}
        catch (InvalidSettingsOfAlgoritm e) {e.GetCause(); LoadStandartSettings(); System.out.println("Standart Settings was loaded");}
    }




    private boolean CheckSettings()
    {boolean t=true;
        if ((p_mut>1||p_mut<0)
                ||(p_cros>1||p_cros<0)
                ||(count_of_cross<0)
                ||(count_of_mut<0)
                ||(Capacity_Generations<0||Capacity_Generations>10000)
                ||(Capacity_Population<2||Capacity_Population>10000)
                ||(Good_Size>0.7*Capacity_Population)
                ||(Bad_Size>0.3*Capacity_Population)
                ||(Best_Size<1 || Best_Size>Capacity_Population*0.2))
            t=false;

        if ((Capacity_Generations>0&&Capacity_Generations<20)
                ||(count_of_cross>150||count_of_cross<50)
                ||(count_of_mut>100||count_of_mut<50)
                ||(Capacity_Population>1000||Capacity_Population<100)
                ||(Good_Size>0.4*Capacity_Population)
                ||(Bad_Size>0.2*Capacity_Population)
                ||(Best_Size>0.01*Capacity_Population))
        {System.out.println("Warning!One of  parametr establish in not standart interval");}
        return t;
    };



    public void LoaMindStandartSettings()
    {p_mut=  0.3;
        p_cros= 0.8;
        count_of_cross=15;//
        count_of_mut=5;//
        Capacity_Generations=3;//300
        Capacity_Population=15;//300
        Good_Size=5;//100
        Bad_Size=2;//40
        Best_Size=3;
        Limit=true;};


    public void LoadStandartSettings()
    {p_mut=  0.3;
        p_cros= 0.8;
        count_of_cross=150;//
        count_of_mut=50;//
        Capacity_Generations=300;//300
        Capacity_Population=300;//300
        Good_Size=100;//100
        Bad_Size=40;//40
        Best_Size=5;
        Limit=true;};

    public void LoadMaxStandartSettings()
    {p_mut=  0.5;
        p_cros= 0.3;
        count_of_cross=550;//
        count_of_mut=350;//
        Capacity_Generations=1000;//2000//1000
        Capacity_Population=2000;//500
        Good_Size=150;//100
        Bad_Size=50;//40
        Best_Size=5;
        Limit=true;};

    public void LoadMaxxStandartSettings()
    {p_mut=  0.3;
        p_cros= 0.8;
        count_of_cross=1050;//
        count_of_mut=550;//
        Capacity_Generations=5000;//300
        Capacity_Population=1000;//300
        Good_Size=600;//100
        Bad_Size=100;//40
        Best_Size=5;
        Limit=true;};



    public String PrintStandartSettings()
    {String s="p_mut = 0.3"+"\n"+
            "p_cros = 0.8"+"\n"+
            "count_of_cross = 150"+"\n"+
            "count_of_mut = 50"+"\n"+
            "Capacity_Generations = 300"+"\n"+
            "Capacity_Population = 300"+"\n"+
            "Good_Size = 100"+"\n"+
            "Bad_Size = 40"+"\n"+
            "Best_Size = 5"+"\n"+
            "Limit = true";
        return s;};


    public String toString()
    { String s="p_mut = "+p_mut+"\n"+
            "p_cros = "+p_cros+"\n"+
            "count_of_cross = "+count_of_cross+"\n"+
            "count_of_mut = "+count_of_mut+"\n"+
            "Capacity_Generations = "+Capacity_Generations+"\n"+
            "Capacity_Population = "+Capacity_Population+"\n"+
            "Good_Size = "+Good_Size+"\n"+
            "Bad_Size = "+Bad_Size+"\n"+
            "Best_Size = "+Best_Size+"\n"+
            "Limit = "+Limit;
        return s; }


    public void CreateStandartSettingsFile(String  path){CreateStandartSettingsFilex(path);}
    public void CreateStandartSettingsFile(){CreateStandartSettingsFilex("C:\\1\\StandartSettings.txt");}
    public void CreateStandartSettingsFilex(String path) {
        String line_separator = System.getProperty("line.separator");
        String s[]= PrintStandartSettings().split("\n");;
        int i=0;
        File file = new File("C:\\1\\StandartSettings.txt");
        if (!(file.exists() && file.isFile())) {
            try {
                FileWriter OutputStream = new FileWriter(path);
                //Запись с новой строки?
                while (i<s.length)
                {OutputStream.write(s[i]+line_separator);
                    // OutputStream.write(s[i]+" = "+s[i+1]+line_separator);
                    i++;}
                OutputStream.close(); }
            catch(Exception e){e.printStackTrace();}}
    }

}
//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class SettingsForHTML
{ private boolean RejOfSave;
    private double k;
    private int KeySize;

    SettingsForHTML(){};
    SettingsForHTML(double kx,int KeySizeX,boolean RejSaveX)
    {k=kx;KeySize=KeySizeX;RejOfSave=RejSaveX;}
    public void SetRejSave(boolean RejSavex){RejOfSave=RejSavex;};
    public void SetK(int kx){if (CheckK(kx)) k=kx;};
    public void SetKeySize(int KeySizex){if (CheckKeySize(KeySizex)) KeySize=KeySizex;};
    public double GetK(){return k;}
    public int GetKeySize(){return KeySize;}
    public boolean GetRejOfSave(){return RejOfSave;}
    private boolean CheckK(int kx){return (kx>0&&kx<30);};
    private boolean CheckKeySize(int KeySizex){return (KeySizex>5&&KeySizex<30);};
    public boolean Check()throws SettingsForHTMLException {if((KeySize>5&&KeySize<30)&&(k>0&&k<31)) return true; else throw new SettingsForHTMLException();}
    void PrintStandrtSettings(){System.out.println("k=10\nKeySize in [10,15]\nRejOfSave=true");}
}
//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class SaveToHTML
{    private String StandartName;
    SaveToHTML(){ StandartName="h.html";};

    void saveToFile(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett,String PathSave,String Name)
    {try {saveToFilex( SS, Rmas,F, Sett,PathSave+Name+".html");} catch(NullPointerException e){e.getMessage();}}
    void saveToFileDesktop(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett,String Name)
    {try {saveToFilex( SS, Rmas,F, Sett,"C:\\Users\\Олег\\Desktop\\"+Name+".html");} catch(NullPointerException e){e.getMessage();}}
    void saveToFile_1(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett,String Name)
    {try {saveToFilex( SS, Rmas,F, Sett,"C:\\1\\Олег\\Desktop\\"+Name+".html");} catch(NullPointerException e){e.getMessage();}}
    void saveToFile(Set SS,ArrayList<Rectangle>RmasTemp,Field F,SettingsForHTML Sett,String PathSave,String Name)
    {try {Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        saveToFilex( SS, Rmas,F, Sett,PathSave+Name+".html");} catch(NullPointerException e){e.getMessage();}}
    void saveToFileDesktop(Set SS,ArrayList<Rectangle>RmasTemp,Field F,SettingsForHTML Sett,String Name)
    {try {Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        saveToFilex( SS, Rmas,F, Sett,"C:\\Users\\Олег\\Desktop\\"+Name+".html");} catch(NullPointerException e){e.getMessage();}}
    void saveToFile_1(Set SS,ArrayList<Rectangle>RmasTemp,Field F,SettingsForHTML Sett,String Name)
    {try {Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        saveToFilex( SS, Rmas,F, Sett,"C:\\1\\Олег\\Desktop\\"+Name+".html");} catch(NullPointerException e){e.getMessage();}}
    void saveToFile(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett,String PathSave)
    {try {saveToFilex( SS, Rmas,F, Sett,PathSave+StandartName);} catch(NullPointerException e){e.getMessage();}}
    void saveToFileDesktop(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett)
    {try {saveToFilex( SS, Rmas,F, Sett,"C:\\Users\\Олег\\Desktop\\"+StandartName);} catch(NullPointerException e){e.getMessage();}}
    void saveToFile_1(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett)
    {try {saveToFilex( SS, Rmas,F, Sett,"C:\\1\\Олег\\Desktop\\"+StandartName);} catch(NullPointerException e){e.getMessage();}}
    void saveToFile(Set SS,ArrayList<Rectangle>RmasTemp,Field F,SettingsForHTML Sett,String PathSave)
    {try {Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        saveToFilex( SS, Rmas,F, Sett,PathSave+StandartName);} catch(NullPointerException e){e.getMessage();}}
    void saveToFileDesktop(Set SS,ArrayList<Rectangle>RmasTemp,Field F,SettingsForHTML Sett)
    {try {Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        saveToFilex( SS, Rmas,F, Sett,"C:\\Users\\Олег\\Desktop\\"+StandartName);} catch(NullPointerException e){e.getMessage();}}
    void saveToFile_1(Set SS,ArrayList<Rectangle>RmasTemp,Field F,SettingsForHTML Sett)
    {try {Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);
        saveToFilex( SS, Rmas,F, Sett,"C:\\1\\Олег\\Desktop\\"+StandartName);} catch(NullPointerException e){e.getMessage();}}


    public void saveToFilex(Set SS,Rectangle[] Rmas,Field F,SettingsForHTML Sett,String PathSavex)
    {String PathSave;
         boolean RejOfSave;
         //int k;
         double k;
        int KeySize;


        try {Sett.Check();
            k=Sett.GetK();
            //k=0.3;
            KeySize=Sett.GetKeySize();
            RejOfSave=Sett.GetRejOfSave();
            PathSave=PathSavex;

            try { FileWriter MyFile = new FileWriter(PathSave);
                int i=0;
                String line_separator = System.getProperty("line.separator");
                String beg = "<!DOCTYPE html>"+line_separator+
                        "<html>"+line_separator+
                        "<head>"+line_separator+
                        "<meta charset=\"utf-8\">"+line_separator+
                        "<title>Canvas в HTML5</title>"+line_separator+
                        "</head>"+line_separator+
                        "<body>"+line_separator+
                        "<canvas id=\"myCanvas\" width=\"1000\" height=\"1000\" "+line_separator+
                        "style=\"background-color:#eee; border:1px solid #ccc;\">"+line_separator+
                        "Ваш браузер не поддерживает Canvas"+line_separator+
                        "</canvas>"+line_separator+
                        "<script>"+line_separator+
                        "var canvas = document.getElementById(\"myCanvas\"), "+line_separator+
                        "context = canvas.getContext(\"2d\");"+line_separator;

                String beg2="context.beginPath();"+line_separator+
                        "context.rect("+0+","+ 0+","+ F.GetLength()*k+","+ F.GetWidth()*k+")"+";"+line_separator+
                        "context.closePath();"+line_separator+
                        "context.strokeStyle = \"red\""+";"+line_separator+
                        "context.stroke();"+line_separator;

                MyFile.write(beg);
                MyFile.write(beg2);
                while (i<SS.GetCount())
                {String red="#FF0000",
                        green="#00FF00",
                        blue="#1E90FF",
                        pink="#FF69B4",
                        DarkBlue="#0000FF",
                        Aqua="#00FFFF",
                        Yellow="#FFFF00",
                        BlueViolet="#8A2BE2",
                        Magenta="#FF00FF",
                        Black="#000000",
                        temp="";
                    if (i%9==0) temp = red;
                    if (i%9==1) temp = green;
                    if (i%9==2) temp = blue;
                    if (i%9==3) temp = pink;
                    if (i%9==4) temp = DarkBlue;
                    if (i%9==5) temp = Aqua;
                    if (i%9==6) temp = Yellow;
                    if (i%9==7) temp = BlueViolet;
                    if (i%9==8) temp = Magenta;

                    String s ;
                    if (RejOfSave)
                    {s="context.beginPath();"+line_separator+
                            "context.rect("+SS.x[i]*k+","+ SS.y[i]*k+","+ SS.x2[i]*k+","+ SS.y2[i]*k+")"+";"+line_separator+
                            "context.closePath();"+line_separator+
                            "context.fillStyle =\""+ temp+"\""+";"+line_separator+
                            "context.fill();"+line_separator+
                            "context.strokeStyle = \""+Black+"\""+";"+line_separator+
                            "context.stroke();"+line_separator;}


                    else {s="context.beginPath();"+line_separator+
                            "context.rect("+SS.x[i]*k+","+ SS.y[i]*k+","+ SS.x2[i]*k+","+ SS.y2[i]*k+")"+";"+line_separator+
                            "context.closePath();"+line_separator+
                            // "context.fillStyle =\""+ temp+"\""+";"+line_separator+
                            // "context.fill();"+line_separator+
                            "context.strokeStyle = \""+temp+"\""+";"+line_separator+
                            "context.stroke();"+line_separator;}

                    MyFile.write(s); i++;}


                i=0;
                String s= "context.font =\""+KeySize+"px Verdana\"" + line_separator+"context.fillStyle = \"Black\";"+line_separator;
                MyFile.write(s);

                while (i<SS.GetCount())
                {String s2="context.fillText("+SS.set[i]+ ","+ (10+SS.x[i]*k)+","+ (20+SS.y[i]*k)+");"+line_separator+
                        "context.fillText(\"L="+ Rmas[SS.set[i]].getlength()+ "\","+ (10+SS.x[i]*k)+","+ (20+SS.y[i]*k+KeySize)+");"+line_separator+
                        "context.fillText(\"W="+ Rmas[SS.set[i]].getwidth()+ "\","+ (10+SS.x[i]*k)+","+ (20+SS.y[i]*k+2*KeySize)+");"+line_separator;
                    MyFile.write(s2);
                    i++;}

                String s3="context.fillText(\"CountOfRectangles = "+SS.GetCount()+ "\","+ (20+F.GetLength()*k)+","+ (50)+");"+line_separator;
                MyFile.write(s3);

                i=0;

                while (i<SS.GetCount())
                {String s2="context.fillText(\"N["+i+"]"+ " = "+"Rectangle ["+SS.set[i]+"]"+ "\","+ (20+F.GetLength()*k)+","+ (50+20*(i+2))+");"+line_separator;
                    MyFile.write(s2);
                    i++;}


                ArrayList<Integer> arrayList = new ArrayList<>();

                for (i=0;i<SS.GetCount();i++)
                {arrayList.add(SS.set[i]);}


                i=0;
                int j=0;

                while (i<SS.set.length)
                { if(arrayList.contains(i)) ;
                else {String s2="context.fillText(\"Rectangle["+i+"]"+ " is not used"+ "\","+ (200+F.GetLength()*k)+","+ (50+20*(j+2))+");"+line_separator;
                    MyFile.write(s2);
                    j++;
                    arrayList.add(i);}
                    i++; }


                String end="</script>"+line_separator+
                        "</body>"+line_separator+
                        "</html>"+line_separator;
                MyFile.write(end);

                MyFile.close();}
            catch(IOException e){e.printStackTrace();}
        }catch (SettingsForHTMLException e) {e.GetCause();}
    }
}

//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class LoadRectangles
{ boolean View;

    LoadRectangles(Boolean v) {View=v;};
    LoadRectangles(){};

    public void SetView(Boolean v){View=v;}
    public void GetView(){System.out.println("View = "+View);}


    public ArrayList<Rectangle> LoadRect(String s){ return LoadRectx(s); }
    public ArrayList<Rectangle> LoadRectDesktop(){ return LoadRectx("C:\\Users\\Олег\\Desktop\\rect.txt"); }
    public ArrayList<Rectangle> LoadRect_1(){ return LoadRectx("C:\\1\\rect.txt"); }
    public ArrayList<Rectangle> LoadRectDesktop(String Name){ return LoadRectx("C:\\Users\\Олег\\Desktop\\"+Name); }
    public ArrayList<Rectangle> LoadRect_1(String Name){ return LoadRectx("C:\\1\\"+Name); }


    public ArrayList<Rectangle> LoadRectx(String s)
    {  ArrayList<String> StringList = new ArrayList<>();
        String line_separator = System.getProperty("line.separator");

        try {
            try {
                try {
                    FileReader InputStream = new FileReader(s);
                    Scanner scan = new Scanner(InputStream);//.useDelimiter("\\;");
                    while (scan.hasNextLine()) {
                        String s2[] = scan.nextLine().split(",");   // |\.|;|!|\?    (" = \\|| =\\||= ");
                        if (s2.length > 5) throw new InvalidFillingOfFile();//уточнить длину после split
                        StringList.add(s2[1].trim());
                        StringList.add(s2[2].trim());
                    }

                    InputStream.close();


                    if (View) for (String sx : StringList) System.out.println(sx);


                    ArrayList<Rectangle> RmasTemp = new ArrayList<>();

                    int i = 0;
                    while (i < StringList.size()) {
                        int x, y;
                        x = Integer.parseInt(StringList.get(i));
                        i++;
                        y = Integer.parseInt(StringList.get(i));
                        i++;
                        RmasTemp.add(new Rectangle(x, y));
                    }


                    // Rectangle[] Rmas= (Rectangle[])RmasTemp.toArray(new Rectangle[RmasTemp.size()]);

                    int k = 0;
                    if (View) {
                        for (Rectangle sx : RmasTemp) {
                            System.out.println("Rmas[" + k + "]" + sx);
                        }
                        k++;
                    }

                    return RmasTemp;
                } catch (InvalidFillingOfFile e) {
                    e.GetCause();
                    return null;
                }
            } catch (FileNotFoundException e) {
                e.getMessage();
                return null;
            }
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }
}


//--------------------------------------------------
//--------------------------------------------------
//--------------------------------------------------
class Retext{
    public void Refile(String s,String s2)
    {try{
        try {FileReader InputStream = new FileReader(s);
            FileWriter OutputStream = new FileWriter(s2);
            Scanner scan = new Scanner(InputStream);
            String line_separator = System.getProperty("line.separator");


            while (scan.hasNextLine()) {
                String s3[] = scan.nextLine().split(" ");
                OutputStream.write(s3[0]+"),"+s3[1]+","+s3[2]+line_separator);
                if(scan.hasNextLine())scan.nextLine();
            }

            InputStream.close();
            OutputStream.close();


        } catch (FileNotFoundException e) {e.getMessage(); }
    } catch (IOException e) {e.getMessage();}

    }
}


