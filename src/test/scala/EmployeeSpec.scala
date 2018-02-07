import com.greyamigo.EmployeeExercise._
import org.scalatest._

class EmployeeSpec extends WordSpec with Matchers {

  val projectA:Project = "ProjectA"
  val projectB:Project = "ProjectB"
  val projectC:Project = "ProjectC"

  val simon   = Employee("Simon")
  val lisa    = Employee("Lisa", simon)
  val leonard = Employee("Leonard", simon)

  val john   = Employee("John", lisa,    List(projectA,projectB))
  val jack   = Employee("Jack", lisa,    List(projectA))
  val james  = Employee("James",leonard, List(projectA, projectC))
  val lucy   = Employee("Lucy", leonard, List(projectB, projectC))
  val sam    = Employee("Sam",  leonard, List(projectB))

  val asgard= Organisation("Asgard",List(simon, lisa, leonard, john,jack, james, lucy, sam))

  "Employee Exercise" should {
    "find mates in the same project (1)" in {
      getMates(asgard, james) shouldBe (List(john, jack, lucy))
    }
    "find mates in the same project (2)" in {
      getMates(asgard, lucy) shouldBe List(john, sam, james)
    }
    "find common manager (1)" in {
      getClosestManager(asgard, john, jack) shouldBe Some(lisa)
    }
    "find common manager (2)" in {
      getClosestManager(asgard, john, sam) shouldBe Some(simon)
    }
    "guess who is in charge (1)" in {
      guessWhoseResponsible(asgard, projectA) shouldBe lisa
    }
    "guess who is in charge (2)" in {
      guessWhoseResponsible(asgard, projectB) shouldBe leonard
    }
  }
}
