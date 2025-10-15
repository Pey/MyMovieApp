package pr.peyman.movieapptest.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.savedstate.SavedState
import dagger.hilt.android.AndroidEntryPoint
import pr.peyman.movieapptest.R
import pr.peyman.movieapptest.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            //navigation
            navController = findNavController(R.id.nav_host)
            //
            bottomNav.setupWithNavController(navController)


            navController.addOnDestinationChangedListener(object :
                NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle? // important
                ) {
                    if (destination.id == R.id.splashFragment || destination.id == R.id.registerFragment || destination.id == R.id.detailsFragment) {
                        bottomNav.visibility = View.GONE
                    } else {
                        bottomNav.visibility = View.VISIBLE
                    }
                }
            })

        }

    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}