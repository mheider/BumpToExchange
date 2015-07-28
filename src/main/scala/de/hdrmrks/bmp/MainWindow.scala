/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Markus Heider
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
