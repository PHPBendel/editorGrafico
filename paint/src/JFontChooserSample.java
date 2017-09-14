
import say.swing.JFontChooser;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Font;

public class JFontChooserSample
{
	public static void main (String[]args)
	{
		try
		{
			UIManager.getInstalledLookAndFeels();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			final JFontChooser fontChooser = new JFontChooser();

			final JFrame window = new JFrame("JFontChooser Sample");
			final JButton showButton = new JButton("Select Font");
			showButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					int result = fontChooser.showDialog(window);
					if (result == JFontChooser.OK_OPTION)
					{
						Font font = fontChooser.getSelectedFont(); 
						showButton.setFont(font);
						window.pack();
						System.out.println("Selected Font : " + font); 
					}
				}
			});
			window.getContentPane().add(showButton, BorderLayout.CENTER);
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			window.setLocationRelativeTo(null);
			window.pack();
			window.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
