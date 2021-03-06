package interface

import engine.{Train, World}
import utils.Pos

import scalafx.scene.Node
import scalafx.scene.control.Label
import scalafx.scene.layout.BorderPane

object LocalInformationPanel extends GUIComponent {

  private val localInformationPane: BorderPane = new BorderPane
  private val noInfoLabel = new Label("No town selected")

  def make() : Node = {
    localInformationPane.style = "-fx-background-color: lightCoral"
    localInformationPane.center = noInfoLabel
    localInformationPane
  }

  def restart(): Unit = {
    localInformationPane.center = noInfoLabel
  }

  def displayElementInfoAt(pos : Pos): Unit = {
    World.updatableAt(pos) match {
      case Some(train : Train) =>
        OneTrainInformationPanel.addPanel(train.propertyPane())
        WorldCanvas.selectTrain(train.pos)
      case Some(e) => localInformationPane.center = e.propertyPane()
      case None => localInformationPane.center = noInfoLabel
    }
  }

}
