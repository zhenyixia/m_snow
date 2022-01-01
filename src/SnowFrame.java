import javax.swing.JFrame;

/**
 * @author Administrator
 * @since 2022/1/1 15:28
 **/
public class SnowFrame extends JFrame{
  private SnowPanel snowPanel;

  public void addPanel(){
    this.snowPanel = new SnowPanel();
    add(this.snowPanel);
    this.snowPanel.startDown();
  }

  public void showMe(){
    setVisible(true);
    setSize(1000, 600);
    setTitle("月夜雪景");
    setDefaultCloseOperation(3);
    addPanel();
  }
}