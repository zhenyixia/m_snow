/**
 * @author Administrator
 * @since 2022/1/1 15:29
 **/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class SnowPanel extends JPanel{
  // 用xx，yy表示所有雪花的坐标的x轴与y轴坐标
  private int[] xx = new int[1000];

  private int[] yy = new int[1000];

  private Font[] fs;

  private int x = 120;

  private int y = 100;

  // 实际雪花 落下的高度
  private int high = 1000;

  // 实际雪花 落下的宽度
  private int width = 1900;

  // 月食速度系数，越大则速度越慢
  private int speed = 2;

  //构造器初始化所有雪花的坐标与字体数组
  public SnowPanel(){
    int i;
    for(i = 0; i < this.xx.length; i++){
      this.xx[i] = (int)(Math.random() * width);
      this.yy[i] = (int)(Math.random() * high);
    }
    this.fs = new Font[12];
    for(i = 0; i < this.fs.length; i++)
      this.fs[i] = new Font("宋体", 1, i + 12);
  }

  // 重写paint方法，画月亮，月食，与雪景
  @Override
  public void paint(Graphics g){
    super.paint(g);
    setBackground(Color.BLACK);
    g.setColor(Color.white);
    g.fillArc(60, 40, 70, 70, 0, 360);
    g.setColor(Color.black);
    g.fillArc(this.x, this.y, 70, 70, 0, 360);
    g.setColor(Color.white);
    for(int i = 0; i < this.xx.length; i++){
      g.setFont(this.fs[i % 10]);
      g.drawString("*", this.xx[i], this.yy[i]);
    }
  }

  // 开始下雪，
  public void startDown(){
    (new Thread(() -> {
      int speed = SnowPanel.this.speed;
      while(true){
        // 控制月食运动
        if(SnowPanel.this.x >= 0 || SnowPanel.this.y >= 0){
          speed--;
          if(speed == 0){
            SnowPanel.this.x = SnowPanel.this.x - 1;
            SnowPanel.this.y = SnowPanel.this.y - 1;
            speed = SnowPanel.this.speed;
          }
        }
        // 控制
        for(int i = 0; i < SnowPanel.this.yy.length; i++){
          SnowPanel.this.yy[i] = SnowPanel.this.yy[i] + 1;
          if(SnowPanel.this.yy[i] >= high){
            SnowPanel.this.yy[i] = 0;
          }
        }
        SnowPanel.this.repaint();
        try{
          Thread.sleep(30L);
        }catch(InterruptedException e){
          e.printStackTrace();
        }
      }
    })).start();
  }
}
