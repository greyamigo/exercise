package com.greyamigo

import scala.annotation.tailrec
import scala.collection.mutable.HashMap

object EmployeeExercise {

  type Project = String
  case class Organisation (name:String, employees:List[Employee])
  case class Employee(
                       name: String,
                       lineManger: Option[Employee]= None,
                       projects: List[Project] = Nil
                     )

  implicit def anyToOption[A](a: A): Option[A] = Option(a)

  case class Organization(employees:List[Employee])


  /**
    * For a given organisation returns the set of projects
    * @param org [[Organisation]]
    * @return Set[Project]
    */
  def getAllProjects(org:Organisation):Set[Project] = {
    org.employees.foldLeft(List[Project]())((acc, emp)=> acc ++ emp.projects).toSet

  }

  /**
    *
    * @param org
    * @return
    */
  def getProjectMembers(org:Organisation):HashMap[Project,List[Employee]] = {
    val projectMembers = HashMap.empty[Project,List[Employee]]
    getAllProjects(org).map((f)=> org.employees.map((emp)=>
      if (emp.projects.contains(f)) {
        projectMembers.get(f) match{
          case Some(empList) => projectMembers.update(f, empList++ List(emp))
          case None          => projectMembers.update(f,List(emp))
        }
      }))
    projectMembers
  }

  /**
    *
    * @param org
    * @param emp
    * @return
    */
  def getMates(org:Organisation, emp:Employee):List[Employee]={
    emp.projects match{
      case Nil  => List()
      case projects => projects.foldLeft(List[Employee]())((acc, ele)=> {
        if (getAllProjects(org).contains(ele))
          acc ++ getProjectMembers(org).get(ele).get.filter(_ != emp)
        else List()
      })
    }
  }

  /**
    *
    * @param organisation
    * @param employee
    * @return
    */
  def getManagers(organisation: Organisation, employee: Employee):Set[Employee]={
    @tailrec
    def getManagersAcc(organisation: Organisation,employee: Employee, acc:Set[Employee]):Set[Employee] ={
      employee.lineManger match {
        case None => acc
        case Some(manager) => getManagersAcc(organisation,manager,acc + manager)
      }
    }
    getManagersAcc(organisation,employee,Set[Employee]())
  }

  /**
    *
    * @param organisation
    * @param emp1
    * @param emp2
    * @return
    */
  def getClosestManager(organisation: Organisation, emp1:Employee, emp2: Employee): Option[Employee]={
    (getManagers(organisation,emp1) intersect(getManagers(organisation,emp2))).headOption
  }

  /**
    *
    * @param org
    * @param project
    * @return
    */
  def getReportingToCounts(org:Organisation, project: Project) = {
    val acc = HashMap[Employee, Int]()
    getProjectMembers(org).get(project).get.map((ele)=>{
      ele match{
        case Employee(_,Some(emp),_) =>{
          if (acc.contains(emp))
            acc.update(emp, (acc.get(emp).get)+1 )
          else
            acc.update(emp,1)
        }
        case Employee(_,None,_) => acc
      }
    })
    acc
  }

  /**
    *
     * @param organisation
    * @param project
    * @return
    */
 def guessWhoseResponsible(organisation: Organisation, project: Project):Employee = {
   getReportingToCounts(organisation,project).maxBy(_._2)._1
 }
}
