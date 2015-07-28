package de.hdrmrks.bmp

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{BorderLayout, Dimension}
import javax.swing._

object MainWindow extends App {

  val mainFrame: JFrame = new JFrame("new titel")
  val dropLabel: JLabel = new JLabel("Hier eine Datei reinwerfen!")
  val button: JButton = new JButton("Drueck mich")

  private def setup  = {
    button.addActionListener(new MyActionListener (event => {
      JOptionPane.showMessageDialog(mainFrame, "Hey :3")
    }))

    mainFrame.setLayout(new BorderLayout)
    mainFrame.add(dropLabel, BorderLayout.CENTER)
    mainFrame.add(button, BorderLayout.SOUTH)

    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    mainFrame.setLocationByPlatform(true)

    mainFrame.setVisible(true)

    mainFrame.pack
  }

  setup
}

class MyActionListener(f: ActionEvent => Unit) extends ActionListener {
  override def actionPerformed(event: ActionEvent) = { f(event) }
}
