package engine

import utils.Pos

import scala.util.Random

abstract class Town(_pos : Pos, _name : String) extends Updatable {

  val MAX_POPULATION = 1000000
  val MIN_POPULATION = 10
  val DEFAULT_PROPORTION_TRAVELER = 0.1

  var station : Option[Station] = None
  var offer : Offer = new Offer
  var request : Request = new Request

  var population : Int = MIN_POPULATION +
      new Random().nextInt(MAX_POPULATION + 1 - MIN_POPULATION)

  var proportionTraveler : Double = DEFAULT_PROPORTION_TRAVELER

  def pos: Pos = _pos
  def name : String = _name
  def hasStation : Boolean = station.nonEmpty

  def nbWaitingPassengers : Int = {
    if (hasStation) station.get.nbWaitingPassengers() else 0
  }

  @throws(classOf[CannotBuildItemException])
  def buildStation()

  @throws(classOf[CannotBuildItemException])
  def buildTrain()

  def sendPeopleToNeighbours(number : Int): Unit

  def sendPeople(to : Station, nbPassenger : Int)

  def explore()
  def produce()

}
