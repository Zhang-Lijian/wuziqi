package wuziqi;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;

//实现对GoBangframe下棋界面的监听接口处理
public class frameListener implements GoBangConfig, MouseListener {
	public GoBangframe gf;
//	public int turn = 1;// 判断当前轮到谁了，1表示黑方，2表示白方

	public frameListener(GoBangframe gf) {
		this.gf = gf;
	}
	public void mouseClicked(java.awt.event.MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		// 计算棋子要落在棋盘的哪个交叉点上
		int colu = x / 40;
		int ro = y / 40;
		Graphics g = gf.getGraphics();

		if (gf.isAvail[colu][ro]!=0) {
			System.out.println(gf.isAvail[colu][ro]);
			System.out.println("此处已经有棋子了，请下在其它地方");
		} else {
			// 当前位置可以落子，先计算棋盘上棋子在数组中相应的位置
			if (gf.turn == 1) {
				// 先设置颜色
				g.setColor(Color.black);
				// 落子
				g.fillOval(colu * size, ro * size, size, size);
				// 设置当前位置已经有棋子了,棋子为黑子
				gf.isAvail[colu][ro] = 1;				
			} else {
				g.setColor(Color.white);
				g.fillOval(colu * size, ro * size, size, size);
				// 设置当前位置已经有棋子了，棋子为白子
				gf.isAvail[colu][ro] = 2;				
			}
			gf.turn=-gf.turn;
		}
		gf.cpList.add(new ChessPosition(colu, ro));
		if (isWin(colu, ro)) {
			if (gf.turn == 1)
				System.out.println("白方赢");
			else
				System.out.println("黑方赢");
		}
	}
	public boolean isWin(int colu, int ro) {
		boolean flag = false;
		// 判断是否已经出现五科棋子了
		// 列判断
		// 首先界定数组范围，防止越界
		int imin = colu - 4, imax = colu + 4;
		if (imin < 0)
			imin = 0;
		if (imax > 14)
			imax = 14;
		int count1 = 0;// 判断相连的棋子数
		for (int i = imin; i <= imax; i++) {
			if (gf.isAvail[i][ro] == 1)
				count1++;
			// 如果出现了其他棋子，或者是没有棋子时，就重新开始计数
			else
				count1 = 0;
			if (count1 == 5) {
				flag = true;
			}
		}

		// 行判断
		// 首先界定数组范围，防止越界
		int jmin = ro - 4, jmax = ro + 4;
		if (jmin < 0)
			jmin = 0;
		if (jmax > 14)
			jmax = 14;
		int count2 = 0;// 判断相连的棋子数
		for (int j = jmin; j <= jmax; j++) {
			if (gf.isAvail[colu][j] == 1)
				count2++;
			else
				count2 = 0;
			if (count2 == 5) {
				flag = true;
			}
		}

		// 135度判断
		// 首先界定数组范围，防止越界
		int count3 = 0;// 判断相连的棋子数
		for (int i = -4; i <= 4; i++) {
			if ((colu + i >= 0) && (ro + i >= 0) && (colu + i <= 14) && (ro + i <= 14)) {
				if (gf.isAvail[colu + i][ro + i] == 1)
					count3++;
				// 如果出现了其他棋子，或者是没有棋子时，就重新开始计数
				else
					count3 = 0;
				if (count3 == 5) {
					flag = true;
				}
			}
		}
		int count4 = 0;// 判断相连的棋子数
		for (int i = -4; i <= 4; i++) {
			if ((colu + i >= 0) && (ro - i >= 0) && (colu + i <= 14) && (ro - i <= 14)) {
				// System.out.print("count4:"+count4);
				if (gf.isAvail[colu + i][ro - i] == 1)
					count4++;
				// 如果出现了其他棋子，或者是没有棋子时，就重新开始计数
				else
					count4 = 0;
				if (count4 == 5) {
					flag = true;
				}
			}
		}
		return flag;
	}

	// Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
	public void mousePressed(java.awt.event.MouseEvent e) {

	}

	// Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
	public void mouseReleased(java.awt.event.MouseEvent e) {

	}

	// Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
	public void mouseEntered(java.awt.event.MouseEvent e) {

	}

	// Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
	public void mouseExited(java.awt.event.MouseEvent e) {

	}
}