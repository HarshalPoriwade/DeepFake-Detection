package org.example.project.domain.UseCase

import kotlinx.coroutines.flow.Flow
import org.example.project.data.ApiResponse.NewResponse
import org.example.project.data.stateHandler.ApiResult
import org.example.project.domain.Repository.Repository

class NewsPredictionUseCase(private val repository: Repository) {

    suspend operator fun invoke(claim: String): Flow<ApiResult<NewResponse>> {
        return repository.newsPrediction(claim = claim)
    }
}