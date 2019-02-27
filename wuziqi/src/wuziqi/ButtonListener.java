package wuziqi;

//设置按钮监听方法ButttonLitener类
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//实现对JPanel的监听接口处理
public class ButtonListener implements GoBangConfig, ActionListener {
	public GoBangframe gf;

	public ButtonListener(GoBangframe gf) {
		this.gf = gf;// 获取左半部分的画板
	}

	// 当界面发生操作时进行处理
	@Override
	public void actionPerformed(ActionEvent e) {

		// 获取当前被点击按钮的内容，判断是不是"开始新游戏"这个按钮
		if (e.getActionCommand().equals("开始新游戏")) {
			for(int i=0;i<15;i++) {
				for(int j=0;j<15;j++) {
					gf.isAvail[i][j]=0;
				}
			}
			
			// 如果是开始新游戏的按钮，再为左半部分设置监听方法
//			gf.addMouseListener(new frameListener(gf));
			gf.repaint();
		}
		if(e.getActionCommand().equals("悔棋")) {
			ChessPosition cp = gf.cpList.get(gf.cpList.size()-1);
			gf.isAvail[cp.Listi][cp.Listj]=0;
			gf.cpList.remove(gf.cpList.size()-1);
			gf.turn=-gf.turn;
			gf.repaint();
		}
		if(e.getActionCommand().equals("认输")) {
			if(gf.turn==1) {
				System.out.println("黑方认输");
			}else {
				System.out.println("白方认输");
			}
		}
	}

}