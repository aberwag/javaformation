package org.loi.formation.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoizAspect {
	
	
@Before("execution(public String mouchardToString())")
public void logAvantMethode() {
	System.out.println("Advice before avant méthode \"mouchardToString\" objet pojouser");
}

}
